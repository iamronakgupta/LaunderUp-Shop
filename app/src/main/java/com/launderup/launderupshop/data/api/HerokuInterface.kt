package com.launderup.launderupshop.data.api

import com.launderup.launderupshop.data.models.ShopLogin
import com.launderup.launderupshop.data.models.ShopRegister
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
interface HerokuInterface {
    @POST("api/shoplogin")
    fun userLogin(
        @Query("phone")
        mobileNumber:Long,
    ):Call<ShopLogin>

    @Headers(
        "Accept: application/json"
    )
    @POST("api/register")
    fun shopRegister(

        @Query("shid")
        shid:String,
        @Query("shop_owner_details")
        shopOwnerDetail: JSONObject,
        @Query("shop_details")
        shopDetail: JSONObject,
        @Query("shop_documents")
        shopDocument: JSONObject,
        @Query("profile_image")
        profileImage:String,
        @Query("pan_image")
        panImage:String,
        @Query("shop_license_image")
        licenseImage:String

    ):Call<Status>

    @POST("api/register")
    fun register(@Body dataModal: ShopRegister?): Call<Status?>?
}