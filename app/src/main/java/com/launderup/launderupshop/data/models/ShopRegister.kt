package com.launderup.launderupshop.data.models

import org.json.JSONObject

data class ShopRegister(
    var shid:String,
    var shopOwnerDetail: JSONObject,
    var shopDetail: JSONObject,
    var shopDocument: JSONObject

)