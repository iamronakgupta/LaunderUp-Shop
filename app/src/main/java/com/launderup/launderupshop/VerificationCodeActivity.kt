package com.launderup.launderupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class VerificationCodeActivity : AppCompatActivity() {
    lateinit var backArrow: ImageView
    lateinit var verifyButton: Button
    lateinit var otp1:EditText
    lateinit var otp2:EditText
    lateinit var otp3:EditText
    lateinit var otp4:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)
        backArrow = findViewById(R.id.back_arrow)
        verifyButton=findViewById(R.id.verify_button)
        otp1=findViewById(R.id.otp1)
        otp2=findViewById(R.id.otp2)
        otp3=findViewById(R.id.otp3)
        otp4=findViewById(R.id.otp4)

        backArrow.setOnClickListener {
            finish()
        }

        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        otp1.addTextChangedListener(GenericTextWatcher(otp1, otp2))
        otp2.addTextChangedListener(GenericTextWatcher(otp2, otp3))
        otp3.addTextChangedListener(GenericTextWatcher(otp3, otp4))
        otp4.addTextChangedListener(GenericTextWatcher(otp4, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        otp1.setOnKeyListener(GenericKeyEvent(otp1, null))
        otp2.setOnKeyListener(GenericKeyEvent(otp2, otp1))
        otp3.setOnKeyListener(GenericKeyEvent(otp3, otp2))
        otp4.setOnKeyListener(GenericKeyEvent(otp4,otp3))

        //Event that will happen on when we click on Verify

        verifyButton.setOnClickListener {
            verify()
        }

    }

    private fun verify() {
        if( otp1.text.toString().trim()==""||
            otp2.text.toString().trim()==""||
            otp3.text.toString().trim()==""||
            otp4.text.toString().trim()==""){
            Toast.makeText(this,"Otp Not Entered",Toast.LENGTH_LONG).show()
            return
        }
        var otpString=""
        otpString+=otp1.text.toString()
        otpString+=otp2.text.toString()
        otpString+=otp3.text.toString()
        otpString+=otp4.text.toString()
        Toast.makeText(this,"OTP is "+otpString,Toast.LENGTH_LONG).show()

        val intent = Intent(this,ShopInformation::class.java)
        startActivity(intent)
    }
}
class GenericKeyEvent internal constructor(private val currentView: EditText, private val previousView: EditText?) : View.OnKeyListener{
    override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otp1 && currentView.text.isEmpty()) {
            //If current is empty then previous EditText's number will also be deleted
            previousView!!.text = null
            previousView.requestFocus()
            return true
        }
        return false
    }


}

class GenericTextWatcher internal constructor(private val currentView: View, private val nextView: View?) :
    TextWatcher {
    override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
        val text = editable.toString()
        when (currentView.id) {
            R.id.otp1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp3 -> if (text.length == 1) nextView!!.requestFocus()
            //You can use EditText4 same as above to hide the keyboard
        }
    }

    override fun beforeTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) { // TODO Auto-generated method stub
    }

    override fun onTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) { // TODO Auto-generated method stub
    }

}