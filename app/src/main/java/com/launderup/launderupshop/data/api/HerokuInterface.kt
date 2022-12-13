package com.launderup.launderupshop.data.api

import com.google.gson.JsonElement
import com.launderup.launderupshop.data.models.OrdersResponse
import com.launderup.launderupshop.data.models.ShopDetailResponse
import com.launderup.launderupshop.data.models.ShopLogin
import com.launderup.launderupshop.data.models.StatsResponse
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

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

        @Part("profile_image")
        profileImage:MultipartBody.Part,

        @Part("pan_image")
        panImage:MultipartBody.Part,
        
        @Part("shop_license_image")
        licenseImage:MultipartBody.Part

    ):Call<Status>

    @Multipart
    @Headers(
        "Accept: application/json",

    )
    @POST("api/register")
    fun register(

        @Query("shid")
        shid:String,

        @Query("shop_owner_details")
        shopOwnerDetail: JSONObject,

        @Query("shop_details")
        shopDetail: JSONObject,

        @Query("shop_documents")
        shopDocument: JSONObject,

        @Part
        profileImage:MultipartBody.Part?,

        @Part
        panImage:MultipartBody.Part?,

        @Part
        licenseImage:MultipartBody.Part?

    ):Call<Status>

    @POST("api/inventory")
    fun inventory(
        @Query("shid")
        shid:String,
        @Query("cloth_types")
        clothTypes:JSONObject
    ):Call<JsonElement>

    @Headers(
        "Accept: application/json"
    )
    @POST("api/updateShopDetails")
    fun updateShopDetails(
        @Query("shid")
        shid:String,
        @Query("shop_name")
        shopName:String,
        @Query("shop_address")
        shopAddress:String,
        @Query("shop_phone_no")
        shopPhoneNo:String,
        @Query("days_open")
        daysOpen:String,
        @Query("services_available")
        serviceAvailable:String,
        @Query("operational_hours")
        operationHours:String

    ):Call<JSONObject>

    @Headers(
        "Accept: application/json"
    )
    @POST("api/updateOrder")
    fun updateOrder(
        @Query("shid")
        shid: String,
        @Query("uid")
        uid:String,
        @Query("cloth_order_id")
        orderId:String,
        @Query("option")
        nextStatus: String
    ):Call<JSONObject>


    @GET("api/shopFetch/{shid}")
    fun getShopDetail(
        @Path("shid")
        shid:String
    ):Call<ShopDetailResponse>



    @Headers(
        "Accept: application/json"
    )
    @GET("api/shopOrderFetch/{shid}/{status}")
    fun getShopOrder(
        @Path("shid")
        shid:String,
        @Path("status")
        status:String,
        @Query("page")
        page:Int=1
    ):Call<OrdersResponse>



    @GET("api/stats/{shid}/{type}")
    fun stats(
        @Path("shid")
        shid: String,
        @Path("type")
        type:String
    ):Call<StatsResponse>



    @GET("api/expressChange/{shid}/{status}")
    fun expressChange(
        @Path("shid")
        shid:String,
        @Path("status")
        status:String
    ):Call<JSONObject>


    @Multipart
    @Headers(
        "Accept: application/json"
    )
    @POST("api/changeProfileForm")
    fun changeProfile(
        @Query("shid")
        shid: String,
        @Part image:MultipartBody.Part
    ):Call<JSONObject>
}