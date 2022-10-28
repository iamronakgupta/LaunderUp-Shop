package com.launderup.launderupshop.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.launderup.launderupshop.R
import com.launderup.launderupshop.utils.Resource

class MainActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT:Long=2000
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)




        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            onNewToken(it.result)
        }

        //Putting delay in activity
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable{

                if(sharedPreferences.contains("Registered")) {

                        startActivity(Intent(this, FragmentNavigationActivity::class.java))
                }
                else
                    startActivity(Intent(this, LoginActivity::class.java))


                finish()


        },SPLASH_SCREEN_TIME_OUT)


    }

    private fun onNewToken(token: String) {
        Log.d("Token", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
    }




}