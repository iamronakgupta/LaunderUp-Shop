package com.launderup.launderupshop.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.ResendOTP
import com.launderup.launderupshop.data.models.ShopLogin
import com.launderup.launderupshop.data.models.VerifyOTP
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.api.RetrofitInstance.Companion.api
import com.launderup.launderupshop.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerificationCodeActivity : AppCompatActivity() {

    lateinit var verifyButton: Button
    lateinit var resendOTPButton: Button
    lateinit var otp1:EditText
    lateinit var otp2:EditText
    lateinit var otp3:EditText
    lateinit var otp4:EditText
    lateinit var otp5:EditText
    lateinit var otp6:EditText
    lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)
        verifyButton=findViewById(R.id.verify_button)
        resendOTPButton=findViewById(R.id.resend_otp)
        otp1=findViewById(R.id.otp1)
        otp2=findViewById(R.id.otp2)
        otp3=findViewById(R.id.otp3)
        otp4=findViewById(R.id.otp4)
        otp5=findViewById(R.id.otp5)
        otp6=findViewById(R.id.otp6)
        val mob=intent.getLongExtra("mobile_number",123456789)


        sharedPreferences=this.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)

        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        otp1.addTextChangedListener(GenericTextWatcher(otp1, otp2))
        otp2.addTextChangedListener(GenericTextWatcher(otp2, otp3))
        otp3.addTextChangedListener(GenericTextWatcher(otp3, otp4))
        otp4.addTextChangedListener(GenericTextWatcher(otp4, otp5))
        otp5.addTextChangedListener(GenericTextWatcher(otp5,otp6))
        otp6.addTextChangedListener(GenericTextWatcher(otp6,null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        otp1.setOnKeyListener(GenericKeyEvent(otp1, null))
        otp2.setOnKeyListener(GenericKeyEvent(otp2, otp1))
        otp3.setOnKeyListener(GenericKeyEvent(otp3, otp2))
        otp4.setOnKeyListener(GenericKeyEvent(otp4,otp3))
        otp5.setOnKeyListener(GenericKeyEvent(otp5,otp4))
        otp6.setOnKeyListener(GenericKeyEvent(otp6,otp5))

        //Event that will happen on when we click on Verify
        verifyButton.setOnClickListener {
            check(mob)

        }

        resendOTPButton.setOnClickListener {
            resend(mob)
        }

    }

    private fun check(mob:Long) {
        if( otp1.text.toString().trim()==""||
            otp2.text.toString().trim()==""||
            otp3.text.toString().trim()==""||
            otp4.text.toString().trim()==""||
            otp5.text.toString().trim()==""||
            otp6.text.toString().trim()==""){
            Toast.makeText(this,"Otp Not Entered",Toast.LENGTH_LONG).show()
            return
        }
        var otpString=""
        otpString+=otp1.text.toString()
        otpString+=otp2.text.toString()
        otpString+=otp3.text.toString()
        otpString+=otp4.text.toString()
        otpString+=otp5.text.toString()
        otpString+=otp6.text.toString()
        verify(otpString.toInt(),mob)

    }

    private fun verify(otp:Int, mobile_number:Long){
        val verify=api.verifyOTP(otp =otp, mobileNumber = mobile_number)
        verify.enqueue(object : Callback<VerifyOTP> {
            override fun onResponse(call: Call<VerifyOTP>, response: Response<VerifyOTP>) {
                if(response.body()!!.type == "success"){
                    userLogin(mobile_number)
                }
                else
                    Toast.makeText(applicationContext, "Wrong OTP", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<VerifyOTP>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }

    private fun resend(mobile_number:Long){
        val resend=api.resendOTP(mobileNumber = mobile_number)
        resend.enqueue(object : Callback<ResendOTP> {
            override fun onResponse(call: Call<ResendOTP>, response: Response<ResendOTP>) {
            }
            override fun onFailure(call: Call<ResendOTP>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })

    }
    private fun userLogin(number:Long) {
        val userLogin= HerokuInstance.herokuapi.userLogin(mobileNumber = number)
        userLogin.enqueue(object : Callback<ShopLogin> {
            override fun onResponse(call: Call<ShopLogin>, response: Response<ShopLogin>) {
                if(response.body()!!.account_status=="Created"|| response.body()!!.account_status=="Created but phone number exists"){
                    val mobile:String=number.toString().replace("91","")
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("shid", response.body()!!.shid)
                        .putString("mobileNumber",mobile)
                    editor.apply()

                    startActivity(Intent(this@VerificationCodeActivity, ShopInformation::class.java))
                }
                else if(response.body()!!.account_status=="Logged In"){
                    val mobile:String=number.toString().replace("91","")
                    val editor:SharedPreferences.Editor=sharedPreferences.edit()
                    editor.putString("shid",response.body()!!.shid)
                        .putString("mobileNumber",mobile)
                        .putString("Registered", "true")
                    editor.apply()

                    startActivity(Intent(this@VerificationCodeActivity, FragmentNavigationActivity::class.java))
                }
            }
            override fun onFailure(call: Call<ShopLogin>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "User Login Credentials Save Failed")
            }
        })
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
    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (currentView.id) {
            R.id.otp1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp3 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp4 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.otp5 -> if (text.length == 1) nextView!!.requestFocus()
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