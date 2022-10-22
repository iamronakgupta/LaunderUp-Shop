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
import com.launderup.launderupshop.data.ClothEntity
import com.launderup.launderupshop.data.ShopDetail
import com.launderup.launderupshop.data.ShopOwnerDetail
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
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

    private var men=ArrayList<ClothEntity>()
    private var women=ArrayList<ClothEntity>()
    private var kids = ArrayList<ClothEntity>()


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
        getItemsList()

        menClothListAdapter = ClothListAdapter(this,men)
        menRecyclerView.layoutManager= LinearLayoutManager(this)
        menRecyclerView.adapter=menClothListAdapter

        womenClothListAdapter = ClothListAdapter(this,women)
        womenRecyclerView.layoutManager= LinearLayoutManager(this)
        womenRecyclerView.adapter=womenClothListAdapter

        kidsClothListAdapter = ClothListAdapter(this,kids)
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


        jsonObject.append("mens",menClothJSONArray)
        jsonObject.append("womens",womenClothJSONArray)
        jsonObject.append("kids",kidsClothJSONArray)




        ShopDetail.cloth_types=jsonObject

        val intent2 = Intent(this, LegalDocumentsActivity::class.java)



        Log.e("Shop Details",ShopDetail.cloth_types.toString())
        Log.e("Shop Owner Details",ShopOwnerDetail.toString())

        startActivity(intent2)

    }

    private fun getItemsList(){


        //mens cloths
        men.add(ClothEntity("Bathrobe",null,null))
        men.add(ClothEntity("Cap",null,null))
        men.add(ClothEntity("Coat",null,null))
        men.add(ClothEntity("Gentssuit",null,null))
        men.add(ClothEntity("Halfpants",null,null))
        men.add(ClothEntity("Halfsweater",null,null))
        men.add(ClothEntity("Handkerchief",null,null))
        men.add(ClothEntity("Jacket",null,null))
        men.add(ClothEntity("Jeans",null,null))
        men.add(ClothEntity("Jumpsuit",null,null))
        men.add(ClothEntity("Kurta",null,null))
        men.add(ClothEntity("Leatherjacket",null,null))
        men.add(ClothEntity("Overcoat",null,null))
        men.add(ClothEntity("Shawl",null,null))
        men.add(ClothEntity("Sherwani",null,null))
        men.add(ClothEntity("Shirt",null,null))
        men.add(ClothEntity("Shoes",null,null))
        men.add(ClothEntity("Shorts",null,null))
        men.add(ClothEntity("Socks",null,null))
        men.add(ClothEntity("Sweater",null,null))
        men.add(ClothEntity("Sweatshirt",null,null))
        men.add(ClothEntity("Tie",null,null))
        men.add(ClothEntity("Trackpants",null,null))
        men.add(ClothEntity("Trousers",null,null))
        men.add(ClothEntity("Tshirt",null,null))
        men.add(ClothEntity("Undergarments",null,null))
        men.add(ClothEntity("Waistcoat",null,null))


        //womens cloths
        women.add(ClothEntity("Bathrobe",null,null))
        women.add(ClothEntity("Blouse",null,null))
        women.add(ClothEntity("Blouse",null,null))
        women.add(ClothEntity("Cap",null,null))
        women.add(ClothEntity("CholilehengaDupatta",null,null))
        women.add(ClothEntity("Coat",null,null))
        women.add(ClothEntity("Dupatta",null,null))
        women.add(ClothEntity("Gowndress",null,null))
        women.add(ClothEntity("Halfpants",null,null))
        women.add(ClothEntity("Halfsweater",null,null))
        women.add(ClothEntity("Handkerchief",null,null))
        women.add(ClothEntity("Jacket",null,null))
        women.add(ClothEntity("Jeans",null,null))
        women.add(ClothEntity("Jumpsuit",null,null))
        women.add(ClothEntity("Kurti",null,null))
        women.add(ClothEntity("Ladiessuit",null,null))
        women.add(ClothEntity("Leatherjacket",null,null))
        women.add(ClothEntity("Nighty",null,null))
        women.add(ClothEntity("Overcoat",null,null))
        women.add(ClothEntity("Purse",null,null))
        women.add(ClothEntity("Saree",null,null))
        women.add(ClothEntity("Shawl",null,null))
        women.add(ClothEntity("Shirt",null,null))
        women.add(ClothEntity("Shoes",null,null))
        women.add(ClothEntity("Shorts",null,null))
        women.add(ClothEntity("Skirt",null,null))
        women.add(ClothEntity("Socks",null,null))
        women.add(ClothEntity("Sweater",null,null))
        women.add(ClothEntity("Sweatshirt",null,null))
        women.add(ClothEntity("Tie",null,null))
        women.add(ClothEntity("Trackpants",null,null))
        women.add(ClothEntity("Trousers",null,null))
        women.add(ClothEntity("Tshirt",null,null))
        women.add(ClothEntity("Undergarments",null,null))
        women.add(ClothEntity("Waistcoat",null,null))


        //kids cloths
        kids.add(ClothEntity("Bathrobe",null,null))
        kids.add(ClothEntity("Blouse",null,null))
        kids.add(ClothEntity("Cap",null,null))
        kids.add(ClothEntity("CholilehengaDupatta",null,null))
        kids.add(ClothEntity("Coat",null,null))
        kids.add(ClothEntity("Dupatta",null,null))
        kids.add(ClothEntity("Gentssuit",null,null))
        kids.add(ClothEntity("Gowndress",null,null))
        kids.add(ClothEntity("Halfpants",null,null))
        kids.add(ClothEntity("Halfsweater",null,null))
        kids.add(ClothEntity("Handkerchief",null,null))
        kids.add(ClothEntity("Jacket",null,null))
        kids.add(ClothEntity("Jeans",null,null))
        kids.add(ClothEntity("Jumpsuit",null,null))
        kids.add(ClothEntity("Kurta",null,null))
        kids.add(ClothEntity("Kurti",null,null))
        kids.add(ClothEntity("Ladiessuit",null,null))
        kids.add(ClothEntity("Leatherjacket",null,null))
        kids.add(ClothEntity("Nighty",null,null))
        kids.add(ClothEntity("Overcoat",null,null))
        kids.add(ClothEntity("Purse",null,null))
        kids.add(ClothEntity("Saree",null,null))
        kids.add(ClothEntity("Shawl",null,null))
        kids.add(ClothEntity("Sherwani",null,null))
        kids.add(ClothEntity("Shirt",null,null))
        kids.add(ClothEntity("Shoes",null,null))
        kids.add(ClothEntity("Shorts",null,null))
        kids.add(ClothEntity("Skirt",null,null))
        kids.add(ClothEntity("Socks",null,null))
        kids.add(ClothEntity("Sweater",null,null))
        kids.add(ClothEntity("Sweatshirt",null,null))
        kids.add(ClothEntity("Tie",null,null))
        kids.add(ClothEntity("Trackpants",null,null))
        kids.add(ClothEntity("Trousers",null,null))
        kids.add(ClothEntity("Tshirt",null,null))
        kids.add(ClothEntity("Undergarments",null,null))
        kids.add(ClothEntity("Waistcoat",null,null))



    }
    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
}