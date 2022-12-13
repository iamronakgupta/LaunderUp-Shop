package com.launderup.launderupshop.ui.fragments

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.ShopDetailResponse
import com.launderup.launderupshop.ui.view.ProfileUpdateActivity
import com.launderup.launderupshop.ui.view.REQUEST_CODE
import com.launderup.launderupshop.utils.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


class ProfileFragment : Fragment() {
    private var profileImage: String =""
    private lateinit var nameTv:TextView
    private lateinit var addressTv:TextView
    private lateinit var phoneTv:TextView
    private lateinit var editProfileButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private var shid:String="d"
    private lateinit var progressBar:ProgressBar
    private lateinit var profileIv:ImageView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val root=inflater.inflate(R.layout.fragment_profile, container, false)
        editProfileButton=root.findViewById(R.id.edit_profile_btn)
        nameTv=root.findViewById(R.id.profile_name_tv)


        addressTv=root.findViewById(R.id.profile_address_tv)
        phoneTv=root.findViewById(R.id.profile_phoneno_tv)
        progressBar=root.findViewById(R.id.progress_bar)
        profileIv=root.findViewById(R.id.profile_iv)
        sharedPreferences= context?.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)!!
        shid = sharedPreferences.getString("shid",null).toString()


        loadUi()


        editProfileButton.setOnClickListener{
            val intent= Intent(activity,ProfileUpdateActivity::class.java)
            startActivity(intent)


        }

        profileIv.setOnClickListener {
            changeDp()
        }

        return root
    }

    private fun changeDp() {

        openGalleryForImage()
        
    }

    private fun loadUi() {
        val getDetail =  HerokuInstance.herokuapi.getShopDetail(shid)
        getDetail.enqueue(object : Callback<ShopDetailResponse> {
            override fun onResponse(call: Call<ShopDetailResponse>, response: Response<ShopDetailResponse>) {

                if(response.code()==200){
                    val shopDetail = response.body()
                    shopDetail?.let { updateView(it) }
                    val editor = sharedPreferences.edit()
                    val gson = Gson()
                    editor.putString("ShopDetail",gson.toJson(shopDetail))
                    editor.putString("ClothTypes", shopDetail!!.cloth_types)
                    editor.apply()


                }else{
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(context,"Something Wrong :)",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ShopDetailResponse>, t: Throwable) {

                Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.INVISIBLE
            }

        })
    }

    fun updateView(shopDetails: ShopDetailResponse) {

        progressBar.visibility = View.INVISIBLE
        nameTv.text = "Shop Name :" + shopDetails?.shop_name
        addressTv.text = "Address : " +shopDetails?.shop_address
        phoneTv.text = "Phone : " + shopDetails?.shop_phone_no
        context?.let { Glide.with(this).load(shopDetails.image_url).into(profileIv)}
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        progressBar.visibility = View.VISIBLE
        if (requestCode== REQUEST_CODE && resultCode== Activity.RESULT_OK&&data!=null){

            val imageStream = data.data?.let { context?.contentResolver?.openInputStream(it) }
            val selectedImageFile:File = File.createTempFile("Not Found","Not Found")
            if (imageStream != null) {
                selectedImageFile.copyInputStreamToFile(imageStream)
            }

            val selectedImage = BitmapFactory.decodeStream(imageStream)


            //profileImage= ImageToBase64.encodeImage(selectedImage)

            Log.i("Profile Image ",profileImage)
            uploadProfile(selectedImageFile)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadProfile(profileImage:File) {
        val body = MultipartBody.Part.createFormData("image", "profile",profileImage.asRequestBody())

        val profileChange = HerokuInstance.herokuapi.changeProfile(shid,body)
        profileChange.enqueue(object :Callback<JSONObject>{
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if(response.code()==200){
                    Toast.makeText(context,"Profile Image Updated",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Profile Image Not Updated",Toast.LENGTH_SHORT).show()
                }
                progressBar.visibility = View.INVISIBLE
            }


            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
               Toast.makeText(context,"Something Wrong :)",Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.INVISIBLE
            }

        })
    }
    private fun File.copyInputStreamToFile(inputStream: InputStream) {
        this.outputStream().use { fileOut ->
            inputStream.copyTo(fileOut)
        }
    }


}