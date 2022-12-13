package com.launderup.launderupshop.repository

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.ShopDetailResponse
import com.launderup.launderupshop.ui.fragments.ProfileFragment
import com.launderup.launderupshop.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepository :ViewModel(){
    lateinit var sharedPreferences: SharedPreferences

    fun getShopDetail(context: Context,type:String):ShopDetailResponse? {


        var output:ShopDetailResponse? = null
        sharedPreferences=context.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)
        val shid:String = sharedPreferences.getString("shid","").toString()
        val editor = sharedPreferences.edit()
        val gson = Gson()



        if(type =="cloth"){
            val clothString = sharedPreferences.getString("ClothTypes",null)
            val shopDetailResponse = ShopDetailResponse(clothString,null,null,null,null,null
            ,null,null,null,null,null,null,null)

            if(clothString!=null){
                return shopDetailResponse
            }

        }else{
            val shopDetailString = sharedPreferences.getString("ShopDetail",null)
            if(shopDetailString!=null) {

                return gson.fromJson(shopDetailString, ShopDetailResponse::class.java)
            }

        }


//        val getDetail=  HerokuInstance.herokuapi.getShopDetail(shid)
//
//
//        getDetail.enqueue(object :Callback<ShopDetailResponse>{
//            override fun onResponse(call: Call<ShopDetailResponse>, response: Response<ShopDetailResponse>) {
//                output= response.body()!!
//                editor.putString("ShopDetail",gson.toJson(output))
//                editor.putString("ClothTypes", output!!.cloth_types)
//                editor.apply()
//                Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
//
//                if(type!="cloth") {
//                    output = ShopDetailResponse(
//                        output!!.cloth_types,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null
//                    )
//                }
//                ProfileFragment().updateView(output!!)
//
//            }
//
//            override fun onFailure(call: Call<ShopDetailResponse>, t: Throwable) {
//
//                Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//        Toast.makeText(context,output.toString(),Toast.LENGTH_SHORT).show()
        return output

    }


}