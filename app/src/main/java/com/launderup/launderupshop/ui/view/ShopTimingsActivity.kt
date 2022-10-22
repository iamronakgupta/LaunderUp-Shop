package com.launderup.launderupshop.ui.view

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.Images
import com.launderup.launderupshop.data.ShopDetail
import com.launderup.launderupshop.data.ShopOwnerDetail
import com.launderup.launderupshop.utils.ImageToBase64
import com.launderup.launderupshop.ui.view.ShopTypeClothsActivity
import java.io.Serializable

const val REQUEST_CODE = 100

class ShopTimingsActivity : AppCompatActivity() {
    private lateinit var uploadImageButton: ImageButton
    private lateinit var closeTimeButton: LinearLayout
    private lateinit var openTimeButton: LinearLayout
    private lateinit var nextButton: Button
    private lateinit var backArrow:ImageView
    private lateinit var openingTimeTv:TextView
    private lateinit var closingTimeTv:TextView
    private lateinit var monCb:CheckBox
    private lateinit var tuesCb:CheckBox
    private lateinit var wednesCb:CheckBox
    private lateinit var thursCb:CheckBox
    private lateinit var friCb:CheckBox
    private lateinit var satCb:CheckBox
    private lateinit var sunCb:CheckBox
    private var profileImage = ""

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
        monCb=findViewById(R.id.monday_checkbox)
        tuesCb=findViewById(R.id.tuesday_checkbox)
        wednesCb=findViewById(R.id.wednesday_checkbox)
        thursCb=findViewById(R.id.thursday_checkbox)
        friCb=findViewById(R.id.friday_checkbox)
        satCb=findViewById(R.id.saturday_checkbox)
        sunCb=findViewById(R.id.sunday_checkbox)

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
                val openingTimeString= "${picker.hour}:${picker.minute}"
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
                val closingTimeString= "${picker.hour}:${picker.minute}"
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

            nextOnClick()

        }
    }

    private fun nextOnClick() {
        if(openingTimeTv.text.toString()==closingTimeTv.text.toString()){
            Toast.makeText(this,"Time Not Selected",Toast.LENGTH_SHORT).show()
            return
        }
        val time:String =  openingTimeTv.text.toString()+closingTimeTv.text.toString()
        var days = ""


        if (monCb.isChecked){
            days += "Monday"
        }
        if (tuesCb.isChecked){
            days += ",Tuesday"
        }
        if (wednesCb.isChecked){
            days += ",Wednesday"
        }
        if (thursCb.isChecked){
            days += ",Thursday"
        }
        if (friCb.isChecked){
            days += ",Friday"
        }
        if (satCb.isChecked){
            days += ",Saturday"
        }
        if (sunCb.isChecked){
            days += ",Sunday"
        }

        if(days.isEmpty()){
            Toast.makeText(this,"Days Not Selected",Toast.LENGTH_SHORT).show()
            return
        }
        if(profileImage.isEmpty()){
            Toast.makeText(this,"Profile Image Not Selected",Toast.LENGTH_SHORT).show()
            return
        }



        val intent = intent

        ShopDetail.operational_hours=time
        ShopDetail.days_open=days
        Images.profile_image = profileImage



        val intent2 = Intent(this, ShopTypeClothsActivity::class.java)

        startActivity(intent2)

    }




    //Upload Image Function
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE)

    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){ result ->

            if(result!=null ){

                val imageStream = contentResolver.openInputStream(result)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                profileImage=ImageToBase64.encodeImage(selectedImage)
                Log.e("BASE64 Profile",profileImage)

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (requestCode== REQUEST_CODE && resultCode==Activity.RESULT_OK&&data!=null){

            val imageStream = data.data?.let { contentResolver.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            uploadImageButton.setImageBitmap(selectedImage)
            profileImage=ImageToBase64.encodeImage(selectedImage)
            Log.e("BASE64 Profile",profileImage)

        }

        super.onActivityResult(requestCode, resultCode, data)


    }

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T


}