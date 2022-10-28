package com.launderup.launderupshop.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import com.launderup.launderupshop.R

class ProfileUpdateActivity : AppCompatActivity() {
    private lateinit var nameEt:TextInputEditText
    private lateinit var emailEt:TextInputEditText
    private lateinit var pincodeEt:TextInputEditText
    private lateinit var mobileEt:TextInputEditText
    private lateinit var cityEt:TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_update)

        nameEt = findViewById(R.id.profile_update_name_et)
        emailEt = findViewById(R.id.profile_update_email_et)
        pincodeEt = findViewById(R.id.profile_update_pin_code_et)
        mobileEt = findViewById(R.id.profile_update_mobile_number_et)
        cityEt = findViewById(R.id.profile_update_city_et)

    }


}