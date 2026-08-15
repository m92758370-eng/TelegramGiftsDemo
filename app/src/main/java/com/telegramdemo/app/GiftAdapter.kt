package com.telegramdemo.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GiftAdapter(
    private val gifts: List<Gift>,
    private val onGiftClick: (Gift) -> Unit
) : RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {

    inner class GiftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji: TextView = view.findViewById(R.id.giftEmoji)
        val name: TextView = view.findViewById(R.id.giftName)
        val price: TextView = view.findViewById(R.id.giftPrice)
        val nftBadge: TextView = view.findViewById(R.id.nftBadge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gift, parent, false)
        return GiftViewHolder(view)
    }

    override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
        val gift = gifts[position]
        holder.emoji.text = gift.emoji
        holder.name.text = gift.name
        holder.price.text = gift.priceLabel
        holder.nftBadge.visibility = if (gift.isNft) View.VISIBLE else View.GONE
        holder.itemView.setOnClickListener { onGiftClick(gift) }
    }

    override fun getItemCount(): Int = gifts.size
}
