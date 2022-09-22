package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {
    lateinit var backArrow:ImageView
    lateinit var proceedButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        backArrow = findViewById(R.id.back_arrow)
        proceedButton=findViewById(R.id.proceed_button)
        backArrow.setOnClickListener {
            finish()
        }
        proceedButton.setOnClickListener {
            val intent = Intent(this,VerificationCodeActivity::class.java)
            startActivity(intent)
        }

    }

}