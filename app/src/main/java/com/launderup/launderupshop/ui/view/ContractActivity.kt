package com.launderup.launderupshop.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.ShopDetail
import com.launderup.launderupshop.data.models.ShopDocument
import com.launderup.launderupshop.data.models.ShopOwnerDetail
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.api.Status
import com.launderup.launderupshop.utils.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContractActivity : AppCompatActivity() {
    lateinit var backArrow:ImageView
    lateinit var nextButton: Button
    lateinit var progressBar: ProgressBar
    lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)
        progressBar = findViewById(R.id.progress_bar)

        sharedPreferences=this.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)

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
        shopDetailJson.put("shop_name", ShopDetail.shop_name)
        shopDetailJson.put("cloth_types", ShopDetail.cloth_types)
        shopDetailJson.put("days_open", ShopDetail.days_open)
        shopDetailJson.put("operational_hours", ShopDetail.operational_hours)
        shopDetailJson.put("shop_address", ShopDetail.shop_address)
        shopDetailJson.put("express", ShopDetail.express)
        shopDetailJson.put("shop_phone_no", ShopDetail.shop_phone_no)
        shopDetailJson.put("services_available", ShopDetail.services_available)


        val shopOwnerDetailJson = JSONObject()
        shopOwnerDetailJson.put("owner_name", ShopOwnerDetail.owner_name)
        shopOwnerDetailJson.put("owner_address", ShopOwnerDetail.owner_address)
        shopOwnerDetailJson.put("owner_phone", ShopOwnerDetail.owner_phone)

        val shopDocumentJson = JSONObject()
        shopDocumentJson.put("gst_registered", ShopDocument.gst_registered)
        shopDocumentJson.put("five_percent_gst", ShopDocument.gst_registered)
        shopDocumentJson.put("gst_number", ShopDocument.gst_number)
        shopDocumentJson.put("pan_number", ShopDocument.pan_number)
        shopDocumentJson.put("shop_license_number", ShopDocument.shop_license_number)
        shopDocumentJson.put("address_legal_entity", ShopDocument.address_legal_entity)
        shopDocumentJson.put("entity_name", ShopDocument.entity_name)
        shopDocumentJson.put("bank_name", ShopDocument.bank_name)
        shopDocumentJson.put("bank_account_number", ShopDocument.bank_account_number)
        shopDocumentJson.put("ifsc_code", ShopDocument.ifsc_code)







        Log.e("Data", "onResponse: ${shopDetailJson}")
        val shid = sharedPreferences.getString("shid",null)
        if(shid==null){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        val register = shid?.let {
            HerokuInstance.herokuapi.shopRegister(

                shid = it,
                shopDetail = shopDetailJson,
                shopOwnerDetail = shopOwnerDetailJson,
                shopDocument = shopDocumentJson,
                profileImage = "",
                panImage = "",
                licenseImage = ""
            )
        }
        if (register != null) {
            register.enqueue(object: Callback<Status>{
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    progressBar.visibility = View.INVISIBLE
                    if (response.code()==200){
                        Toast.makeText(this@ContractActivity, "SuccessFul", Toast.LENGTH_SHORT).show()
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("Registered", "true")
                        editor.apply()
                        startActivity(Intent(this@ContractActivity,FragmentNavigationActivity::class.java))
                    }else{
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Response", "onResponse: "+response.toString()+response.code())
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@ContractActivity, "Fail..", Toast.LENGTH_SHORT).show()
                    Log.e("Response", "onResponse: $call $t")
                }

            })
        }
    }
}