package com.launderup.launderupshop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.ClothEntity
import com.launderup.launderupshop.data.models.ShopDetailResponse
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
import com.launderup.launderupshop.utils.ClothData
import com.launderup.launderupshop.utils.Resource
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InventoryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var menClothListAdapter:ClothListAdapter? = null
    private var womenClothListAdapter:ClothListAdapter? = null
    private var kidsClothListAdapter:ClothListAdapter? = null
    private lateinit var menClothRv:RecyclerView
    private lateinit var womenClothRv:RecyclerView
    private lateinit var kidsClothRv:RecyclerView
    private lateinit var nextButton: Button
    private lateinit var progressBar: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    private var shid:String="d"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_inventory, container, false)

        menClothRv = root.findViewById(R.id.mens_types_cloths_input_rv)
        womenClothRv = root.findViewById(R.id.women_types_cloths_input_rv)
        kidsClothRv = root.findViewById(R.id.kids_types_cloths_input_rv)
        nextButton = root.findViewById(R.id.next_button)
        progressBar = root.findViewById(R.id.progress_bar)

        sharedPreferences= context?.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)!!
        shid = sharedPreferences.getString("shid",null).toString()

        nextButton.setOnClickListener {
            nextOnClick()
        }


        val shopDetail = HerokuInstance.herokuapi.getShopDetail(shid)

        shopDetail.enqueue(object :Callback<ShopDetailResponse>{
            override fun onResponse(
                call: Call<ShopDetailResponse>,
                response: Response<ShopDetailResponse>
            ) {
                if(response.code()==200){
                    rvUpdate(response.body())
                }else{
                    Toast.makeText(context,"Error Fetching",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ShopDetailResponse>, t: Throwable) {

                Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
            }

        })


        return root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun nextOnClick() {

        val menCloths = menClothListAdapter?.getList()
        val womenCloths = womenClothListAdapter?.getList()
        val kidsCloths = womenClothListAdapter?.getList()


        menCloths?.removeIf {
            it.rate==null
        }

        womenCloths?.removeIf {
            it.rate==null
        }

        kidsCloths?.removeIf {
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

        val inventory = HerokuInstance.herokuapi.inventory(shid,jsonObject)
        inventory.enqueue(object :  Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if(response.code()==200){
                    Toast.makeText(context,"SuccessFull",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
            }


        })


    }

    fun rvUpdate(shopDetail:ShopDetailResponse?){
        val clothTypes = shopDetail?.cloth_types?.let { JSONObject(it) }
        val gson = GsonBuilder().create()

        val menJsonArray = clothTypes?.get("mens")
        val womenJsonArray = clothTypes?.get("womens")
        val kidsJsonArray = clothTypes?.get("kids")
        Log.i("ClothTypes",clothTypes.toString())
        Log.i("MensCloths",menJsonArray.toString())
        val menClothResponse = gson.fromJson(menJsonArray.toString(),Array<ClothEntity>::class.java).toList()
        val womenClothResponse = gson.fromJson(womenJsonArray.toString(),Array<ClothEntity>::class.java).toList()
        val kidsClothResponse = gson.fromJson(kidsJsonArray.toString(),Array<ClothEntity>::class.java).toList()
        Log.i("MensClothsArray",menClothResponse.toString())

        val mensClothsList = ClothData.menCloths()
        val womenClothsList = ClothData.womenCloths()
        val kidsClothsList = ClothData.kidsCloth()

        if(menClothResponse.isNotEmpty() && mensClothsList.isNotEmpty()){
            for(i in menClothResponse){
                for(j in mensClothsList){
                    if(i.name?.lowercase()==j.name?.lowercase()){
                        j.rate=i.rate
                    }
                }
            }
        }

        if(womenClothResponse.isNotEmpty() && womenClothsList.isNotEmpty()){
            for(i in womenClothResponse){
                for(j in womenClothsList){
                    if(i.name?.lowercase()==j.name?.lowercase()){
                        j.rate=i.rate
                    }
                }
            }
        }


        if(kidsClothResponse.isNotEmpty() && kidsClothsList.isNotEmpty()){
            for(i in kidsClothResponse){
                for(j in kidsClothsList){
                    if(i.name?.lowercase()==j.name?.lowercase()){
                        j.rate=i.rate
                    }
                }
            }
        }



        progressBar.visibility = View.INVISIBLE
        menClothListAdapter = context?.let { ClothListAdapter(it,mensClothsList) }
        menClothRv.layoutManager= LinearLayoutManager(context)
        menClothRv.adapter=menClothListAdapter


        womenClothListAdapter = context?.let { ClothListAdapter(it,womenClothsList) }
        womenClothRv.layoutManager =LinearLayoutManager(context)
        womenClothRv.adapter = womenClothListAdapter


        kidsClothListAdapter = context?.let { ClothListAdapter(it,kidsClothsList) }
        kidsClothRv.layoutManager =LinearLayoutManager(context)
        kidsClothRv.adapter = womenClothListAdapter

    }




}