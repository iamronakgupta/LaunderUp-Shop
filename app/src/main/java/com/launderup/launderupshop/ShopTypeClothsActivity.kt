package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ShopTypeClothsActivity : AppCompatActivity() {
    lateinit var backArrow:ImageView
    lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_type_cloths)
        backArrow=findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.next_button)

        //finish activity on back arrow click
        backArrow.setOnClickListener {
            finish()
        }


        //event that will happen on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this,LegalDocumentsActivity::class.java)
            startActivity(intent)
        }
    }
}