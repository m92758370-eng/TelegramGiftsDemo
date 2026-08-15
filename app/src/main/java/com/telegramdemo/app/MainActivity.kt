package com.telegramdemo.app

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("telegram_demo_prefs", Context.MODE_PRIVATE)

        val giftsRecyclerView = findViewById<RecyclerView>(R.id.giftsRecyclerView)
        giftsRecyclerView.layoutManager = GridLayoutManager(this, 3)
        giftsRecyclerView.adapter = GiftAdapter(GiftData.allGifts()) { gift ->
            showGiftDialog(gift)
        }

        updatePremiumUi()

        findViewById<android.widget.Button>(R.id.premiumButton).setOnClickListener {
            togglePremium()
        }
    }

    private fun showGiftDialog(gift: Gift) {
        val typeLabel = if (gift.isNft) "NFT Collectible Gift" else "Gift"
        AlertDialog.Builder(this)
            .setTitle("${gift.emoji} ${gift.name}")
            .setMessage("$typeLabel\n${gift.priceLabel}\n\nThis is a visual demo only — no real purchase or Telegram account is involved.")
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
        val star = findViewById<android.widget.TextView>(R.id.premiumStar)
        val button = findViewById<android.widget.Button>(R.id.premiumButton)
        val subtitle = findViewById<android.widget.TextView>(R.id.premiumSubtitle)

        star.visibility = if (isPremium) android.view.View.VISIBLE else android.view.View.GONE
        button.text = if (isPremium) "Deactivate Premium" else "Activate Premium"
        subtitle.text = if (isPremium)
            "You're enjoying exclusive features, stickers, badges and more"
        else
            "Unlock exclusive features, stickers, badges and more"
    }
}
