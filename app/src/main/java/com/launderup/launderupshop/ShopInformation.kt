package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ShopInformation : AppCompatActivity() {
    private lateinit var nextButton: Button
    private lateinit var backArrow:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_information)
        nextButton=findViewById(R.id.nextBtn)
        backArrow=findViewById(R.id.back_arrow)

        //closing of activity on click on back arrow
        backArrow.setOnClickListener {
            finish()
        }

        //event that will happen on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this,ShopTimingsActivity::class.java)
            startActivity(intent)
        }
    }
}