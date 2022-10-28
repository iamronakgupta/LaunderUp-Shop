package com.launderup.launderupshop.data.models

import org.json.JSONObject


data class Order(
    val address: String,
    val clothes_types: JSONObject,
    val delivery_dt: String,
    val express: Boolean,
    val geolocation: String,
    val pickup_dt: String,
    val service_type: String,
    val shid: String,
    val status: String,
    val total_cost: String,
    val uid: String
)