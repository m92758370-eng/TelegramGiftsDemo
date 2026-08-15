package com.telegramdemo.app

data class Gift(
    val emoji: String,
    val name: String,
    val isNft: Boolean = false,
    val priceLabel: String = "",
    val collectionNumber: String = ""
)

object GiftData {

    // Regular Telegram gifts (Stars-purchased, non-tradable)
    val regularGifts = listOf(
        Gift("🧸", "Teddy Bear", priceLabel = "15 ⭐", collectionNumber = "#352"),
        Gift("🌹", "Rose", priceLabel = "25 ⭐", collectionNumber = "#1,045"),
        Gift("🎁", "Gift Box", priceLabel = "25 ⭐", collectionNumber = "#349"),
        Gift("🎂", "Birthday Cake", priceLabel = "50 ⭐", collectionNumber = "#51,494"),
        Gift("💝", "Heart Box", priceLabel = "50 ⭐", collectionNumber = "#10,342"),
        Gift("🚀", "Rocket", priceLabel = "50 ⭐", collectionNumber = "#587"),
        Gift("🍾", "Champagne", priceLabel = "50 ⭐", collectionNumber = "#17,429"),
        Gift("💐", "Bouquet", priceLabel = "50 ⭐", collectionNumber = "#310"),
        Gift("🏆", "Trophy", priceLabel = "100 ⭐", collectionNumber = "#312"),
        Gift("💍", "Ring", priceLabel = "100 ⭐", collectionNumber = "#7,188"),
        Gift("💎", "Diamond Ring", priceLabel = "100 ⭐", collectionNumber = "#3,916"),
        Gift("🎩", "Top Hat", priceLabel = "100 ⭐", collectionNumber = "#1,046"),
        Gift("🔮", "Crystal Ball", priceLabel = "100 ⭐", collectionNumber = "#2,201"),
        Gift("🎮", "Jelly Bunny", priceLabel = "100 ⭐", collectionNumber = "#4,410"),
        Gift("🧿", "Evil Eye", priceLabel = "150 ⭐", collectionNumber = "#892"),
        Gift("🕯️", "Candle", priceLabel = "150 ⭐", collectionNumber = "#5,320"),
        Gift("🎗️", "Signet Ring", priceLabel = "150 ⭐", collectionNumber = "#671"),
        Gift("🧢", "Snoop Cap", priceLabel = "200 ⭐", collectionNumber = "#1,899"),
        Gift("🪩", "Disco Ball", priceLabel = "200 ⭐", collectionNumber = "#233"),
        Gift("🦁", "Lunar Snake", priceLabel = "200 ⭐", collectionNumber = "#6,004"),
        Gift("🏵️", "Eternal Rose", priceLabel = "250 ⭐", collectionNumber = "#158"),
        Gift("🎈", "Balloon", priceLabel = "50 ⭐", collectionNumber = "#9,271"),
        Gift("🧧", "Red Envelope", priceLabel = "50 ⭐", collectionNumber = "#3,004"),
        Gift("🦄", "Magic Unicorn", priceLabel = "300 ⭐", collectionNumber = "#412"),
        Gift("👑", "Precious Crown", priceLabel = "300 ⭐", collectionNumber = "#77")
    )

    // Well-known upgraded / limited collectible (NFT) gifts on TON
    val nftGifts = listOf(
        Gift("🐸", "Plush Pepe", isNft = true, priceLabel = "~5,100 TON floor", collectionNumber = "#1,721"),
        Gift("🧢", "Durov's Cap", isNft = true, priceLabel = "Ultra rare", collectionNumber = "#1"),
        Gift("💍", "Diamond Ring — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#8,802"),
        Gift("🔮", "Crystal Ball — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#4,013"),
        Gift("👑", "Precious Crown — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#256"),
        Gift("🦄", "Magic Unicorn — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#690"),
        Gift("🏵️", "Eternal Rose — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#3,381"),
        Gift("🎩", "Top Hat — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#912"),
        Gift("🧿", "Evil Eye — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#5,540"),
        Gift("🪩", "Disco Ball — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#127"),
        Gift("🦁", "Lunar Snake — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#2,875"),
        Gift("🎗️", "Signet Ring — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#634"),
        Gift("🧸", "Teddy Bear — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#9,190"),
        Gift("🚀", "Rocket — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#488"),
        Gift("💎", "Gem Signet — Collectible", isNft = true, priceLabel = "Floor varies", collectionNumber = "#3,102")
    )

    fun allGifts(): List<Gift> = regularGifts + nftGifts
}
