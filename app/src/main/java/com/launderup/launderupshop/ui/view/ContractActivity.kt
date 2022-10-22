package com.launderup.launderupshop.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.Images
import com.launderup.launderupshop.data.ShopDetail
import com.launderup.launderupshop.data.ShopDocument
import com.launderup.launderupshop.data.ShopOwnerDetail
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.api.HerokuInstance.Companion.herokuapi
import com.launderup.launderupshop.data.api.Status
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContractActivity : AppCompatActivity() {
    lateinit var backArrow:ImageView
    lateinit var nextButton: Button
    lateinit var progressBar: ProgressBar
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)
        progressBar = findViewById(R.id.progress_bar)

        //closing current activity on click on back arrow
        backArrow.setOnClickListener {
            finish()
        }

        //moving to next activity on click on next button
        nextButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            shopRegister()
        }

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun shopRegister() {
        

        val shopDetailJson = JSONObject()
        shopDetailJson.append("shop_name",ShopDetail.shop_name)
        shopDetailJson.append("cloth_types",ShopDetail.cloth_types)
        shopDetailJson.append("days_open",ShopDetail.days_open)
        shopDetailJson.append("operational_hours",ShopDetail.operational_hours)
        shopDetailJson.append("shop_address",ShopDetail.shop_address)
        shopDetailJson.append("express",ShopDetail.express)
        shopDetailJson.append("shop_phone_no",ShopDetail.shop_phone_no)
        shopDetailJson.append("services_available",ShopDetail.services_available)


        val shopOwnerDetailJson = JSONObject()
        shopOwnerDetailJson.append("owner_name",ShopOwnerDetail.owner_name)
        shopOwnerDetailJson.append("owner_address",ShopOwnerDetail.owner_address)
        shopOwnerDetailJson.append("owner_phone",ShopOwnerDetail.owner_phone)

        val shopDocumentJson = JSONObject()
        shopDetailJson.append("gst_registered",ShopDocument.gst_registered)
        shopDetailJson.append("five_percent_gst",ShopDocument.five_percent_gst)
        shopDetailJson.append("gst_number",ShopDocument.gst_number)
        shopDetailJson.append("pan_number",ShopDocument.pan_number)
        shopDetailJson.append("shop_license_number",ShopDocument.shop_license_number)
        shopDetailJson.append("address_legal_entity",ShopDocument.address_legal_entity)
        shopDetailJson.append("entity_name",ShopDocument.entity_name)
        shopDetailJson.append("bank_name",ShopDocument.bank_name)
        shopDetailJson.append("bank_account_number",ShopDocument.bank_account_number)
        shopDetailJson.append("ifsc_code",ShopDocument.ifsc_code)




        Log.e("Data", "onResponse: $shopDetailJson")

        val register = HerokuInstance.herokuapi.shopRegister(
            shid="shidce73c9f9bdde0ba46ce1e3883667a904fff735b7",
            shopDetail = shopDetailJson,
            shopOwnerDetail = shopOwnerDetailJson,
            shopDocument = shopDocumentJson,
            profileImage = Images.profile_image,
            panImage = Images.pan_image,
            licenseImage = Images.shop_license_image
        )
        register.enqueue(object: Callback<Status>{
            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                progressBar.visibility = View.INVISIBLE
                if (response.code()==200){
                    Toast.makeText(this@ContractActivity, "SuccessFul", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
                Log.e("Response", "onResponse: "+response.body().toString())
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(this@ContractActivity, "Fail..", Toast.LENGTH_SHORT).show()
                Log.e("Response", "onResponse: $call $t")
            }

        })
    }
}