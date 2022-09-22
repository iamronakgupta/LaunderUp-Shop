package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class ShopTimingsActivity : AppCompatActivity() {
    private lateinit var uploadImageButton: ImageButton
    private lateinit var closeTimeButton: ImageView
    private lateinit var openTimeButton: ImageView
    private lateinit var nextButton: Button
    private lateinit var backArrow:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_timings)
        uploadImageButton = findViewById(R.id.upload_image_button)
        closeTimeButton = findViewById(R.id.close_timing_clock)
        openTimeButton = findViewById(R.id.open_timing_clock)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)

        //back arrow event to close activity
        backArrow.setOnClickListener {
            finish()
        }

        //action that will perform on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this,ShopTypeClothsActivity::class.java)
            startActivity(intent)
        }

    }
}