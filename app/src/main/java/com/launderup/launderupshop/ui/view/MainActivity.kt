package com.launderup.launderupshop.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.launderup.launderupshop.R

class MainActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT:Long=2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            onNewToken(it.result)
        }

        //Putting delay in activity
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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