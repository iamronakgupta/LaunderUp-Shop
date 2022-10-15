package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R

class ActiveOrderAdapter (val context: Context, private val items:ArrayList<String>):
    RecyclerView.Adapter<ActiveOrderAdapter.ViewHolder>(){

    private lateinit var mListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.active_order_cardview,parent,false),mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        holder.shopName.text=item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view:View,listener: OnItemClickListener):RecyclerView.ViewHolder(view) {
        val shopImg:ImageView=view.findViewById(R.id.shop_img)
        val shopName:TextView=view.findViewById(R.id.shop_name)
        val completeBtn: Button=view.findViewById(R.id.active_order_complete_btn)

        init {
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}