package com.launderup.launderupshop.data

import org.json.JSONObject

class ShopDetail(

){
    companion object {
        var cloth_types: JSONObject?=null
        var days_open: String?=null
        val express: Boolean?=null
        var operational_hours: String?=null
        var services_available: String?=null
        var shop_address: String?=null
        var shop_name: String?=null
        var shop_phone_no: String?=null

    }
}