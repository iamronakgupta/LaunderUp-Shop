package com.launderup.launderupshop.data.models

data class ShopDetailResponse(
    val cloth_types: String?,
    val created_at: String?,
    val days_open: String?,
    val express: Int?,
    val id: Int?,
    val image_url: String?,
    val operational_hours: String?,
    val services_available: String?,
    val shid: String?,
    val shop_address: String?,
    val shop_name: String?,
    val shop_phone_no: String?,
    val updated_at: String?
)