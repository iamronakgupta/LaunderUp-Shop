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

class AcceptedOrderAdapter(val context: Context, private val items:ArrayList<String>):
    RecyclerView.Adapter<AcceptedOrderAdapter.ViewHolder>(){

    private lateinit var mListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.accepted_order_cardview,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        holder.shopName.text=item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val shopImg: ImageView =view.findViewById(R.id.shop_img)
        val shopName: TextView =view.findViewById(R.id.shop_name)
        val pickedBtn: Button =view.findViewById(R.id.accept_order_picked_up_btn)



    }

}
