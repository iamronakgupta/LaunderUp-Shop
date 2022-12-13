package com.launderup.launderupshop.data.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HerokuInstance {
    companion object{
        private val retrofit by lazy {
            val logging= HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client= OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build()


            Retrofit.Builder()
                .baseUrl("http://18.220.168.57:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val herokuapi by lazy {
            retrofit.create(HerokuInterface::class.java)
        }
    }
}