package com.telegramdemo.app

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: android.content.SharedPreferences

    private lateinit var profilePhoto: ImageView
    private lateinit var profilePhotoPlaceholder: TextView
    private lateinit var channelPhoto: ImageView
    private lateinit var channelPhotoPlaceholder: TextView

    private var pickingChannelPhoto = false

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                contentResolver.takePersistableUriPermission(
                    uri, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: Exception) { }

            if (pickingChannelPhoto) {
                prefs.edit().putString("channel_photo_uri", uri.toString()).apply()
            } else {
                prefs.edit().putString("profile_photo_uri", uri.toString()).apply()
            }
            loadPhotos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("telegram_demo_prefs", Context.MODE_PRIVATE)

        profilePhoto = findViewById(R.id.profilePhoto)
        profilePhotoPlaceholder = findViewById(R.id.profilePhotoPlaceholder)
        channelPhoto = findViewById(R.id.channelPhoto)
        channelPhotoPlaceholder = findViewById(R.id.channelPhotoPlaceholder)

        val giftsRecyclerView = findViewById<RecyclerView>(R.id.giftsRecyclerView)
        giftsRecyclerView.layoutManager = GridLayoutManager(this, 3)
        val allGifts = GiftData.allGifts()
        giftsRecyclerView.adapter = GiftAdapter(allGifts) { gift -> showGiftDialog(gift) }

        findViewById<TextView>(R.id.giftCountLabel).text = "${allGifts.size} gifts"

        setupFloatingGifts(allGifts)

        loadPhotos()
        loadProfileInfo()
        loadChannelInfo()
        updatePremiumUi()

        findViewById<CardView>(R.id.setPhotoButton).setOnClickListener {
            pickingChannelPhoto = false
            pickImageLauncher.launch("image/*")
        }

        findViewById<CardView>(R.id.editInfoButton).setOnClickListener {
            showEditInfoDialog()
        }

        findViewById<CardView>(R.id.addChannelButton).setOnClickListener {
            showEditChannelDialog()
        }

        findViewById<CardView>(R.id.sendGiftsButton).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Send Gifts to Friends")
                .setMessage("This is a visual demo only — no real Telegram account or contacts are involved.")
                .setPositiveButton("OK", null)
                .show()
        }

        findViewById<android.widget.Button>(R.id.premiumButton).setOnClickListener {
            togglePremium()
        }
    }

    private fun setupFloatingGifts(gifts: List<Gift>) {
        val floatViews = listOf(
            findViewById<TextView>(R.id.float1),
            findViewById<TextView>(R.id.float2),
            findViewById<TextView>(R.id.float3),
            findViewById<TextView>(R.id.float4),
            findViewById<TextView>(R.id.float5)
        )
        floatViews.forEachIndexed { index, view ->
            if (index < gifts.size) {
                view.text = gifts[index].emoji
                view.visibility = View.VISIBLE
            }
        }
    }

    private fun loadPhotos() {
        val profileUriStr = prefs.getString("profile_photo_uri", null)
        if (profileUriStr != null) {
            try {
                profilePhoto.setImageURI(Uri.parse(profileUriStr))
                profilePhoto.visibility = View.VISIBLE
                profilePhotoPlaceholder.visibility = View.GONE
            } catch (e: Exception) { }
        }

        val channelUriStr = prefs.getString("channel_photo_uri", null)
        if (channelUriStr != null) {
            try {
                channelPhoto.setImageURI(Uri.parse(channelUriStr))
                channelPhoto.visibility = View.VISIBLE
                channelPhotoPlaceholder.visibility = View.GONE
            } catch (e: Exception) { }
        }
    }

    private fun loadProfileInfo() {
        findViewById<TextView>(R.id.profileName).text =
            prefs.getString("profile_name", "Your Name")
        findViewById<TextView>(R.id.profileBio).text =
            prefs.getString("profile_bio", "")
        findViewById<TextView>(R.id.profileUsername).text =
            prefs.getString("profile_username", "")
    }

    private fun loadChannelInfo() {
        val channelCard = findViewById<CardView>(R.id.channelCard)
        val name = prefs.getString("channel_name", null)
        if (name != null) {
            channelCard.visibility = View.VISIBLE
            findViewById<TextView>(R.id.channelName).text = name
            findViewById<TextView>(R.id.channelSubtitle).text =
                prefs.getString("channel_subtitle", "0 subscribers")
        } else {
            channelCard.visibility = View.GONE
        }
    }

    private fun showEditInfoDialog() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        val padding = (16 * resources.displayMetrics.density).toInt()
        layout.setPadding(padding, padding, padding, padding)

        val nameInput = EditText(this)
        nameInput.hint = "Name"
        nameInput.setText(prefs.getString("profile_name", ""))
        layout.addView(nameInput)

        val bioInput = EditText(this)
        bioInput.hint = "Bio"
        bioInput.setText(prefs.getString("profile_bio", ""))
        layout.addView(bioInput)

        val usernameInput = EditText(this)
        usernameInput.hint = "Username"
        usernameInput.setText(prefs.getString("profile_username", ""))
        layout.addView(usernameInput)

        AlertDialog.Builder(this)
            .setTitle("Edit Info")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                prefs.edit()
                    .putString("profile_name", nameInput.text.toString().ifBlank { "Your Name" })
                    .putString("profile_bio", bioInput.text.toString())
                    .putString("profile_username", usernameInput.text.toString())
                    .apply()
                loadProfileInfo()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditChannelDialog() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        val padding = (16 * resources.displayMetrics.density).toInt()
        layout.setPadding(padding, padding, padding, padding)

        val nameInput = EditText(this)
        nameInput.hint = "Channel name"
        nameInput.setText(prefs.getString("channel_name", ""))
        layout.addView(nameInput)

        val subInput = EditText(this)
        subInput.hint = "Subscribers (e.g. 2 subscribers)"
        subInput.setText(prefs.getString("channel_subtitle", ""))
        layout.addView(subInput)

        val photoButton = android.widget.Button(this)
        photoButton.text = "Set Channel Photo"
        photoButton.setOnClickListener {
            pickingChannelPhoto = true
            pickImageLauncher.launch("image/*")
        }
        layout.addView(photoButton)

        AlertDialog.Builder(this)
            .setTitle("Channel")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                val name = nameInput.text.toString()
                if (name.isNotBlank()) {
                    prefs.edit()
                        .putString("channel_name", name)
                        .putString("channel_subtitle", subInput.text.toString().ifBlank { "0 subscribers" })
                        .apply()
                }
                loadChannelInfo()
            }
            .setNegativeButton("Cancel", null)
            .setNeutralButton("Remove Channel") { _, _ ->
                prefs.edit()
                    .remove("channel_name")
                    .remove("channel_subtitle")
                    .remove("channel_photo_uri")
                    .apply()
                loadChannelInfo()
            }
            .show()
    }

    private fun showGiftDialog(gift: Gift) {
        val typeLabel = if (gift.isNft) "NFT Collectible Gift" else "Gift"
        AlertDialog.Builder(this)
            .setTitle("${gift.emoji} ${gift.name}")
            .setMessage("$typeLabel  ${gift.collectionNumber}\n${gift.priceLabel}\n\nThis is a visual demo only — no real purchase or Telegram account is involved.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun togglePremium() {
        val isPremium = prefs.getBoolean("is_premium", false)
        prefs.edit().putBoolean("is_premium", !isPremium).apply()
        updatePremiumUi()
    }

    private fun updatePremiumUi() {
        val isPremium = prefs.getBoolean("is_premium", false)
        val star = findViewById<TextView>(R.id.premiumStar)
        val button = findViewById<android.widget.Button>(R.id.premiumButton)
        val subtitle = findViewById<TextView>(R.id.premiumSubtitle)

        star.visibility = if (isPremium) View.VISIBLE else View.GONE
        button.text = if (isPremium) "Deactivate Premium" else "Activate Premium"
        subtitle.text = if (isPremium)
            "You're enjoying exclusive features, stickers, badges and more"
        else
            "Unlock exclusive features, stickers, badges and more"
    }
}
