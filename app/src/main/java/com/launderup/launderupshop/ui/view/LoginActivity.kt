package com.launderup.launderupshop.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.SendOTP
import com.launderup.launderupshop.data.api.RetrofitInstance.Companion.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton:Button=findViewById(R.id.login_button)
        val numberTextField:EditText=findViewById(R.id.phone_num)
        val radioGroup:RadioGroup=findViewById(R.id.language_radio_group)
        val checkRadioButtonId= radioGroup.checkedRadioButtonId
        val number=numberTextField.text

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.hindi_radio_button->{
                    Toast.makeText(this, "English Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.english_radio_button->{
                    Toast.makeText(this, "Hindi Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginButton.setOnClickListener {
//            if(number.isNotEmpty())
//            {
//                var number1:String=number.toString()
//                number1= "91$number1"
//                sendOtp(number1.toLong())
//            }
//            else
//                Toast.makeText(this, "Enter Number", Toast.LENGTH_SHORT).show()
            val intent=Intent(applicationContext,VerificationCodeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun sendOtp(number: Long) {
        val getOTP=api.getOTP(mobileNumber = number )
        getOTP.enqueue(object : Callback<SendOTP> {
            override fun onResponse(call: Call<SendOTP>, response: Response<SendOTP>) {
                val intent= Intent(applicationContext,VerificationCodeActivity::class.java)
                intent.putExtra("mobile_number",number)
                startActivity(intent)
            }
            override fun onFailure(call: Call<SendOTP>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }

}