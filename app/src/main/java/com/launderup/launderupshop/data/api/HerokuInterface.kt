package com.launderup.launderupshop.data.api

import com.launderup.launderupshop.data.ShopLogin
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

public interface HerokuInterface {
    @POST("api/shoplogin")
    fun userLogin(
        @Query("phone")
        mobileNumber:Long,
    ):Call<ShopLogin>
}