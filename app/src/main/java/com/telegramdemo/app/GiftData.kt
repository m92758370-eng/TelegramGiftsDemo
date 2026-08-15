package com.telegramdemo.app

data class Gift(
    val emoji: String,
    val name: String,
    val isNft: Boolean = false,
    val priceLabel: String = ""
)

object GiftData {

    // Regular Telegram gifts (Stars-purchased, non-tradable)
    val regularGifts = listOf(
        Gift("🧸", "Teddy Bear", priceLabel = "15 ⭐"),
        Gift("🌹", "Rose", priceLabel = "25 ⭐"),
        Gift("🎁", "Gift Box", priceLabel = "25 ⭐"),
        Gift("🎂", "Birthday Cake", priceLabel = "50 ⭐"),
        Gift("💝", "Heart Box", priceLabel = "50 ⭐"),
        Gift("🚀", "Rocket", priceLabel = "50 ⭐"),
        Gift("🍾", "Champagne", priceLabel = "50 ⭐"),
        Gift("💐", "Bouquet", priceLabel = "50 ⭐"),
        Gift("🏆", "Trophy", priceLabel = "100 ⭐"),
        Gift("💍", "Ring", priceLabel = "100 ⭐"),
        Gift("💎", "Diamond Ring", priceLabel = "100 ⭐"),
        Gift("🎩", "Top Hat", priceLabel = "100 ⭐"),
        Gift("🔮", "Crystal Ball", priceLabel = "100 ⭐"),
        Gift("🎮", "Jelly Bunny", priceLabel = "100 ⭐"),
        Gift("🧿", "Evil Eye", priceLabel = "150 ⭐"),
        Gift("🕯️", "Candle", priceLabel = "150 ⭐"),
        Gift("🎗️", "Signet Ring", priceLabel = "150 ⭐"),
        Gift("🧢", "Snoop Cap", priceLabel = "200 ⭐"),
        Gift("🪩", "Disco Ball", priceLabel = "200 ⭐"),
        Gift("🦁", "Lunar Snake", priceLabel = "200 ⭐"),
        Gift("🏵️", "Eternal Rose", priceLabel = "250 ⭐"),
        Gift("🎈", "Balloon", priceLabel = "50 ⭐"),
        Gift("🧧", "Red Envelope", priceLabel = "50 ⭐"),
        Gift("🦄", "Magic Unicorn", priceLabel = "300 ⭐"),
        Gift("👑", "Precious Crown", priceLabel = "300 ⭐")
    )

    // Well-known upgraded / limited collectible (NFT) gifts on TON
    val nftGifts = listOf(
        Gift("🐸", "Plush Pepe", isNft = true, priceLabel = "~5,100 TON floor"),
        Gift("🧢", "Durov's Cap", isNft = true, priceLabel = "Ultra rare"),
        Gift("💍", "Diamond Ring — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🔮", "Crystal Ball — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("👑", "Precious Crown — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🦄", "Magic Unicorn — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🏵️", "Eternal Rose — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🎩", "Top Hat — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🧿", "Evil Eye — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🪩", "Disco Ball — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🦁", "Lunar Snake — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🎗️", "Signet Ring — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🧸", "Teddy Bear — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("🚀", "Rocket — Collectible", isNft = true, priceLabel = "Floor varies"),
        Gift("💎", "Gem Signet — Collectible", isNft = true, priceLabel = "Floor varies")
    )

    fun allGifts(): List<Gift> = regularGifts + nftGifts
}
