package com.launderup.launderupshop.data

data class ShopLogin(
    val account_status: String,
    val shid: String,
    val token: String,
    val verified_at: Any
)