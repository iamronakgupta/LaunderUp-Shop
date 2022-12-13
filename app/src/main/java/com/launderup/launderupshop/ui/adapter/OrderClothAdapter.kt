package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.OrderClothItem

class OrderClothAdapter(val context:Context,val orderClothItem: ArrayList<OrderClothItem>):
    RecyclerView.Adapter<OrderClothAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.order_cloth_item, parent)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orderClothItem[position]
        holder.clothNameTv.text = item.name
        holder.clothNameTv.text=item.quantity+ " * " + item.rate
    }

    override fun getItemCount(): Int {
        return orderClothItem.size
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val clothNameTv:TextView = view.findViewById(R.id.cloth_name_tv)
        val clothQuantityTv:TextView = view.findViewById(R.id.cloth_quantity_tv)


    }
}