package com.launderup.launderupshop

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT:Long=2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Making activity full screen
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }

        //Putting delay in activity
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable{

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
                finish()


        },SPLASH_SCREEN_TIME_OUT)


    }






}