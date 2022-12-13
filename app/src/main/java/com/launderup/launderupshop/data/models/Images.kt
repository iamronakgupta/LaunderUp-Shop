package com.launderup.launderupshop.data.models

import android.graphics.Bitmap
import java.io.File

class Images(

):java.io.Serializable{
    companion object {
        var profile_image: File? = null
        var pan_image:File?=null
        var shop_license_image:File? =null
    }
}