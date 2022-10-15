package com.launderup.launderupshop.ui.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.launderup.launderupshop.R
import org.w3c.dom.Text
import java.sql.Time

const val REQUEST_CODE = 100

class ShopTimingsActivity : AppCompatActivity() {
    private lateinit var uploadImageButton: ImageButton
    private lateinit var closeTimeButton: LinearLayout
    private lateinit var openTimeButton: LinearLayout
    private lateinit var nextButton: Button
    private lateinit var backArrow:ImageView
    private lateinit var openingTime:Number
    private lateinit var openingTimeTv:TextView
    private lateinit var closingTimeTv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_timings)
        uploadImageButton = findViewById(R.id.upload_image_button)
        closeTimeButton = findViewById(R.id.close_input_layout)
        openTimeButton = findViewById(R.id.open_input_layout)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)

        openingTimeTv=findViewById(R.id.open_timing)
        closingTimeTv=findViewById(R.id.close_timing)

        //back arrow event to close activity
        backArrow.setOnClickListener {
            finish()
        }

        //Opening Time Selector
        openTimeButton.setOnClickListener{
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Shop Opening Time")
                .setHour(12)
                .setMinute(0)
                .build()
                picker.show(supportFragmentManager,"Time Picker")
            picker.addOnPositiveButtonClickListener {
                val openingTimeString:String= "${picker.hour}:${picker.minute}"
                openingTimeTv.text = openingTimeString
            }
            picker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Time Not Selected", Toast.LENGTH_SHORT).show()
            }
        }

        //Closing Time Selector
        closeTimeButton.setOnClickListener{
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Shop Closing Time")
                .setHour(12)
                .setMinute(0)
                .build()
                picker.show(supportFragmentManager,"Time Picker")
            picker.addOnPositiveButtonClickListener {
                val closingTimeString:String= "${picker.hour}:${picker.minute}"
                closingTimeTv.text = closingTimeString
            }
            picker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Time Not Selected", Toast.LENGTH_SHORT).show()
            }
        }

        //Upload Shop Image Button
        uploadImageButton.setOnClickListener{
            openGalleryForImage()
        }

        //action that will perform on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this, ShopTypeClothsActivity::class.java)
            startActivity(intent)
        }
    }

    //Opening Days Selection CheckBox Group
    private fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.monday_checkbox -> {
                    if (checked) {
                       Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.tuesday_checkbox -> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.wednesday_checkbox-> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.thursday_checkbox -> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.friday_checkbox -> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.saturday_checkbox -> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.sunday_checkbox -> {
                    if (checked) {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    //Upload Image Function
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){

            // if multiple images are selected
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount

                for (i in 0 until count) {
                    var imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    //     iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
                }

            } else if (data?.data != null) {
                // if single image is selected

                var imageUri: Uri = data.data!!
                //   iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview

            }
        }
    }

}