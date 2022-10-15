package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
;
class ClothListAdapter (val context: Context, private val items:ArrayList<String>):

    RecyclerView.Adapter<ClothListAdapter.ViewHolder>(){

    private lateinit var mListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cloth_list_cardview,parent,false),mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        holder.clothName.text=item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view:View,listener: OnItemClickListener):RecyclerView.ViewHolder(view) {
        val shopImg:ImageView=view.findViewById(R.id.cloth_img)
        val clothName:TextView=view.findViewById(R.id.cloth_name)

        init {
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}