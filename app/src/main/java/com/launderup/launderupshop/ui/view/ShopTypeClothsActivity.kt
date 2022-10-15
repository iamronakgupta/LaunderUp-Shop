package com.launderup.launderupshop.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
import java.util.*

class ShopTypeClothsActivity : AppCompatActivity() {
    private lateinit var backArrow:ImageView
    private lateinit var nextButton: Button
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_type_cloths)

        backArrow=findViewById(R.id.back_arrow)
        nextButton = findViewById(R.id.next_button)
        recyclerView=findViewById(R.id.types_cloths_input_rv)

        //finish activity on back arrow click
        backArrow.setOnClickListener {
            finish()
        }

        //event on click on next button
        nextButton.setOnClickListener {
            val intent = Intent(this, LegalDocumentsActivity::class.java)
            startActivity(intent)
        }

        val arrayList : ArrayList<String> = getItemsList()
        val clothListAdapter = ClothListAdapter(this,arrayList)

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=clothListAdapter

        clothListAdapter.setOnItemClickListener(object : ClothListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
            }
        })

//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                arrayList.clear()
//                val searchText= newText!!.lowercase(Locale.getDefault())
//                if(searchText.isNotEmpty()){
//                    getItemsList().forEach{
//                        if(it.lowercase(Locale.getDefault()).contains(searchText)){
//                            arrayList.add(it)
//                        }
//                    }
//                    recyclerView.adapter!!.notifyDataSetChanged()
//                }
//                else{
//                    arrayList.clear()
//                    arrayList.addAll(getItemsList())
//                    recyclerView.adapter!!.notifyDataSetChanged()
//                }
//                return false
//            }
//        })
    }

    private fun getItemsList(): ArrayList<String> {
        val list=ArrayList<String>()
        for(i in 1..10){
            list.add("Cloth $i")
        }
        return list
    }
}