package com.launderup.launderupshop.ui.view

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.Images
import com.launderup.launderupshop.data.models.ShopDocument
import com.launderup.launderupshop.utils.ImageToBase64
import java.util.regex.Matcher
import java.util.regex.Pattern

class LegalDocumentsActivity : AppCompatActivity() {
    private lateinit var backArrow:ImageView
    private lateinit var nextButton: Button
    private lateinit var gstNumber: EditText
    private lateinit var panNumber: EditText
    private lateinit var ifscCode:EditText
    private lateinit var gst:RadioGroup
    private lateinit var gst5:RadioGroup
    private lateinit var addressLegalEntity:EditText
    private lateinit var nameLegalEntity:EditText
    private lateinit var panImageBtn: LinearLayout
    private lateinit var licenseImageBtn: LinearLayout
    private lateinit var licenseNumber:EditText
    private lateinit var bankName:EditText
    private lateinit var accountNumber:EditText
    private var panImageString = ""
    private var licenseImageString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal_documents)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)
        gstNumber = findViewById(R.id.gst_number_et)
        panNumber = findViewById(R.id.pan_number_et)
        ifscCode= findViewById(R.id.ifsc_code_et)
        gst= findViewById(R.id.gst_registered_rg)
        gst5=findViewById(R.id.five_gst_rg)
        addressLegalEntity=findViewById(R.id.entity_address_et)
        nameLegalEntity = findViewById(R.id.entity_name_et)
        panImageBtn = findViewById(R.id.pan_upload)
        licenseImageBtn = findViewById(R.id.license_upload)
        licenseNumber = findViewById(R.id.license_no_et)
        bankName = findViewById(R.id.bank_name_et)
        accountNumber = findViewById(R.id.acc_no_et)




        //closing current activity on click on back arrow
        backArrow.setOnClickListener {
            finish()
        }

        //moving to next page on click   on next button
        nextButton.setOnClickListener {
//            val validPanNumber:Boolean=isValidPanCardNo(panNumber.text.toString())
//            val validGstNumber:Boolean=isValidGSTNo(gstNumber.text.toString())
//            val validIfscCode:Boolean=isValidIfscCode(ifscCode.text.toString())
//
//            if(validPanNumber && validGstNumber && validIfscCode){
//                val intent = Intent(this, ContractActivity::class.java)
//                startActivity(intent)
//            }
//
//            else{
//                if(!validPanNumber)
//                    Toast.makeText(this,"Enter Correct PAN Number.",Toast.LENGTH_SHORT).show()
//                else if(!validGstNumber)
//                    Toast.makeText(this,"Enter Correct GST Number.",Toast.LENGTH_SHORT).show()
//                else if(!validIfscCode)
//                    Toast.makeText(this,"Enter Correct IFSC Code.",Toast.LENGTH_SHORT).show()
//                else
//                    Toast.makeText(this,"Enter Details.",Toast.LENGTH_SHORT).show()
//
//            }
            nextOnClick()
            val intent = Intent(this, ContractActivity::class.java)
            startActivity(intent)
        }

        licenseImageBtn.setOnClickListener {
            openGalleryForImage(2)
        }

        panImageBtn.setOnClickListener {
            openGalleryForImage(1)
        }

    }

    private fun nextOnClick() {
            var gstBoolean=false
            var gst5Boolean =false
            val gstNoString = gstNumber.text.toString()
            val panNumberString = panNumber.text.toString()
            val nameLegalEntityString = nameLegalEntity.text.toString()
            val addressLegalEntityString = addressLegalEntity.text.toString()
            val licenseNumberString = licenseNumber.text.toString()
            val bankNameString = bankName.text.toString()
            val accountNumberString = accountNumber.text.toString()
            val ifscCodeString = ifscCode.text.toString()

            if(gst[0].isSelected){
                gstBoolean=true
            }
            if(gst5.get(0).isSelected){
                gst5Boolean = true
            }



//            val validPanNumber:Boolean=isValidPanCardNo(panNumber.text.toString())
//            val validGstNumber:Boolean=isValidGSTNo(gstNumber.text.toString())
//            val validIfscCode:Boolean=isValidIfscCode(ifscCode.text.toString())
//
//                if(!validPanNumber)
//                    Toast.makeText(this,"Enter Correct PAN Number.",Toast.LENGTH_SHORT).show()
//                else if(!validGstNumber)
//                    Toast.makeText(this,"Enter Correct GST Number.",Toast.LENGTH_SHORT).show()
//                else if(!validIfscCode)
//                    Toast.makeText(this,"Enter Correct IFSC Code.",Toast.LENGTH_SHORT).show()
//                else
//                    Toast.makeText(this,"Enter Details.",Toast.LENGTH_SHORT).show()

            ShopDocument.address_legal_entity=addressLegalEntityString
            ShopDocument.gst_registered=gstBoolean
            ShopDocument.five_percent_gst=gst5Boolean
            ShopDocument.gst_number=gstNoString
            ShopDocument.pan_number=panNumberString
            ShopDocument.entity_name= nameLegalEntityString
            ShopDocument.shop_license_number=licenseNumberString
            ShopDocument.bank_name=bankNameString
            ShopDocument.bank_account_number=accountNumberString
            ShopDocument.ifsc_code = ifscCodeString

            Images.pan_image = panImageString
            Images.shop_license_image = licenseImageString

    }

    //function to check validity of GST Number
    private fun isValidGSTNo(gstNo:String?): Boolean {
        val regex = ("^[0-9]{2}[A-Z]{5}[0-9]{4}"
                + "[A-Z]{1}[1-9A-Z]{1}"
                + "Z[0-9A-Z]{1}$")
        val p: Pattern = Pattern.compile(regex)
        if (gstNo==null) {
            return false
        }
        val m: Matcher = p.matcher(gstNo)
        return m.matches()
    }

    // function to check validity of Pan Card Number
    private fun isValidPanCardNo(panCardNo: String?): Boolean {
        val regex = "[A-Z]{5}[0-9]{4}[A-Z]"
        val p = Pattern.compile(regex)
        if (panCardNo == null) {
            return false
        }
        val m = p.matcher(panCardNo)
        return m.matches()
    }

    //function to check validity of IFSC Code
    private fun isValidIfscCode(IFSC_Code: String?):Boolean
    {
        val regex="^[A-Z]{4}0[A-Z0-9]{6}$"
        val p = Pattern.compile(regex)
        if (IFSC_Code==null)
            return false
        val m = p.matcher(IFSC_Code)
        return m.matches()
    }

    private fun openGalleryForImage(requestCode:Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCode)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (resultCode== Activity.RESULT_OK&&data!=null){

            val imageStream = data.data?.let { contentResolver.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            if(requestCode==1){
//                panImageBtn.setImageBitmap(selectedImage)
                panImageString= ImageToBase64.encodeImage(selectedImage)
                Log.e("BASE64 Profile",panImageString)

            }
            if (requestCode==2){
//                licenseImageBtn.background = selectedImage.
                licenseImageString= ImageToBase64.encodeImage(selectedImage)
                Log.e("BASE64 Profile",panImageString)
            }


        }

        super.onActivityResult(requestCode, resultCode, data)


    }

}