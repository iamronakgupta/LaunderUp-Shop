package com.launderup.launderupshop.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
import com.launderup.launderupshop.ui.adapter.NewOrderListAdapter
import java.util.ArrayList

class NewOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        val arrayList : ArrayList<String> = getItemsList()
        val recyclerView: RecyclerView =findViewById(R.id.new_order_rv)
        val newOrderListAdapter = NewOrderListAdapter(this,arrayList)


        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=newOrderListAdapter

        newOrderListAdapter.setOnItemClickListener(object : NewOrderListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
            }
        })

    }

    private fun getItemsList(): ArrayList<String> {
        val list=ArrayList<String>()
        for(i in 1..40){
            list.add("Order $i")
        }
        return list
    }
}