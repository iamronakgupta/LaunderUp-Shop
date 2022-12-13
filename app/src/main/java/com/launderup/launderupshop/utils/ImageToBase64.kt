package com.launderup.launderupshop.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File


class ImageToBase64 {

    companion object{
        fun encodeImage(file:ByteArray):String{
//            val baos = ByteArrayOutputStream()
//            bm.compress(Bitmap.CompressFormat.JPEG,100,baos)
//            val b = baos.toByteArray()

            return Base64.encodeToString(file,Base64.URL_SAFE)

        }

        fun encodeImage(bm:Bitmap):String{
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG,100,baos)
            val b = baos.toByteArray()

            return Base64.encodeToString(b,Base64.URL_SAFE)

        }
    }
}