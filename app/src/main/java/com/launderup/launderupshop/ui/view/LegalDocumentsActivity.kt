package com.launderup.launderupshop.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.launderup.launderupshop.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class LegalDocumentsActivity : AppCompatActivity() {
    private lateinit var backArrow:ImageView
    private lateinit var nextButton: Button
    private lateinit var gstNumber: EditText
    private lateinit var panNumber:EditText
    private lateinit var ifscCode:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal_documents)
        backArrow = findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.nextBtn)
        gstNumber = findViewById(R.id.gst_number_et)
        panNumber = findViewById(R.id.pan_number_et)
        ifscCode= findViewById(R.id.ifsc_code_et)

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
            val intent = Intent(this, ContractActivity::class.java)
            startActivity(intent)
        }

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

}