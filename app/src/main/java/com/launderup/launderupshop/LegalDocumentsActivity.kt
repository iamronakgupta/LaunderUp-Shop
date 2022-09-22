package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LegalDocumentsActivity : AppCompatActivity() {
    lateinit var backArrow:ImageView
    lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal_documents)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)

        //closing current activity on click on back arrow
        backArrow.setOnClickListener {
            finish()
        }

        //moving to next page on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this,ContractActivity::class.java)
            startActivity(intent)
        }

    }
}