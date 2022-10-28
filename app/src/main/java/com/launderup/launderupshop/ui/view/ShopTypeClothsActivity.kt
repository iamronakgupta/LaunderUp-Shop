package com.launderup.launderupshop.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.ClothEntity
import com.launderup.launderupshop.data.models.ShopDetail
import com.launderup.launderupshop.data.models.ShopOwnerDetail
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
import com.launderup.launderupshop.utils.ClothData
import org.json.JSONObject
import java.io.Serializable

class ShopTypeClothsActivity : AppCompatActivity() {
    private lateinit var backArrow:ImageView
    private lateinit var nextButton: Button
    private lateinit var womenRecyclerView: RecyclerView
    private lateinit var menRecyclerView: RecyclerView
    private lateinit var kidRecyclerView: RecyclerView
    private lateinit var menClothListAdapter:ClothListAdapter
    private lateinit var womenClothListAdapter:ClothListAdapter
    private lateinit var kidsClothListAdapter:ClothListAdapter



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_type_cloths)

        backArrow=findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.next_button)
        menRecyclerView=findViewById(R.id.mens_types_cloths_input_rv)
        womenRecyclerView=findViewById(R.id.women_types_cloths_input_rv)
        kidRecyclerView=findViewById(R.id.kids_types_cloths_input_rv)


        //finish activity on back arrow click
        backArrow.setOnClickListener {
            finish()
        }

        //event on click on next button
        nextButton.setOnClickListener {

            nextOnClick()

        }

        menClothListAdapter = ClothListAdapter(this,ClothData.menCloths())
        menRecyclerView.layoutManager= LinearLayoutManager(this)
        menRecyclerView.adapter=menClothListAdapter

        womenClothListAdapter = ClothListAdapter(this,ClothData.womenCloths())
        womenRecyclerView.layoutManager= LinearLayoutManager(this)
        womenRecyclerView.adapter=womenClothListAdapter

        kidsClothListAdapter = ClothListAdapter(this,ClothData.kidsCloth())
        kidRecyclerView.layoutManager= LinearLayoutManager(this)
        kidRecyclerView.adapter=kidsClothListAdapter



    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun nextOnClick() {

        val menCloths = menClothListAdapter.getList()
        val womenCloths = womenClothListAdapter.getList()
        val kidsCloths = womenClothListAdapter.getList()

        menCloths.removeIf {
            it.rate==null
        }

        womenCloths.removeIf {
            it.rate==null
        }

        kidsCloths.removeIf {
            it.rate==null
        }

        Log.e("Mens Cloths",menCloths.toString())
        Log.e("Women Cloths",womenCloths.toString())
        Log.e("Kids Cloths",kidsCloths.toString())



        val gson = Gson()
        val menClothJSONArray = gson.toJsonTree(menCloths)
        val womenClothJSONArray = gson.toJsonTree(womenCloths)
        val kidsClothJSONArray = gson.toJsonTree(kidsCloths)
        val jsonObject = JSONObject()


        jsonObject.put("mens",menClothJSONArray)
        jsonObject.put("womens",womenClothJSONArray)
        jsonObject.put("kids",kidsClothJSONArray)




        ShopDetail.cloth_types=jsonObject

        val intent2 = Intent(this, LegalDocumentsActivity::class.java)



        Log.e("Shop Details", ShopDetail.cloth_types.toString())
        Log.e("Shop Owner Details", ShopOwnerDetail.toString())

        startActivity(intent2)

    }


    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
}