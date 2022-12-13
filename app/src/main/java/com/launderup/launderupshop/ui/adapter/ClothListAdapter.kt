package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.launderup.launderupshop.R
;
import com.launderup.launderupshop.data.models.ClothEntity

class ClothListAdapter (val context: Context, private val items:ArrayList<ClothEntity>):

    RecyclerView.Adapter<ClothListAdapter.ViewHolder>(){


    private lateinit var mListener:OnItemClickListener


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cloth_list_cardview,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val item= items[position]
        holder.clothName.text=item.name

        if(item.rate!=null && item.rate!!.isNotEmpty()){
            holder.rs.setText(item.rate)
        }

        val editText = holder.rs
        try{


        Glide.with(context).load("https://launderup.s3.ap-south-1.amazonaws.com/Clothes_Images/${item.name}.png").into(holder.shopImg);

        }catch (e:Exception){
            Log.i("Cloth Icon Error",e.toString())
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                items.get(holder.adapterPosition).rate=s.toString()
                Log.e("Price",s.toString())
            }
        })



    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val shopImg:ImageView=view.findViewById(R.id.cloth_img)
        val clothName:TextView=view.findViewById(R.id.cloth_name)
        val rs:EditText=view.findViewById(R.id.rs_et)


    }

    fun getList():ArrayList<ClothEntity>{
        return items
    }

}