package com.launderup.launderupshop.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


class ImageToBase64 {

    companion object{
        fun encodeImage(bm:Bitmap):String{
            var baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG,100,baos)
            var b = baos.toByteArray()

            return Base64.encodeToString(b,Base64.DEFAULT)

        }
    }
}