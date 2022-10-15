package com.launderup.launderupshop.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.launderup.launderupshop.R

class ShopInformation : AppCompatActivity() {
    private lateinit var nextButton: Button
    lateinit var shopContactNumber: EditText
    lateinit var ownerContactNumber: EditText
    lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_information)
        nextButton=findViewById(R.id.nextBtn)
        shopContactNumber=findViewById(R.id.shop_contact_et)
        ownerContactNumber=findViewById(R.id.owner_contact_et)
        checkBox=findViewById(R.id.same_as_shop_rb)

        checkBox.setOnClickListener {
            onCheckboxClicked(it)
        }
        //event that will happen on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this, ShopTimingsActivity::class.java)
            startActivity(intent)
        }
    }
    private fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.same_as_shop_rb -> {
                    if (checked) {
                        ownerContactNumber.text = shopContactNumber.editableText
                    } else {
                        ownerContactNumber.text = null
                    }
                }
            }
        }
    }
}