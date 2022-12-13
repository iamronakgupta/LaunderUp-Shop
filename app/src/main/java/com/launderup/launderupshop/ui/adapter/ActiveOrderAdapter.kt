package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.SingleOrderResponse

class ActiveOrderAdapter (val context: Context, private val items:ArrayList<SingleOrderResponse>,val listener:ClickListener):
    RecyclerView.Adapter<ActiveOrderAdapter.ViewHolder>(){

    private var mListener = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.active_order_cardview,parent,false),mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items[position]
        holder.indexTv.text=position.toString()
        holder.orderId.text="Order Id : " + item.order_id
        holder.pickedDate.text="Pickup Date : " + getDate(item.pickup_dt) + "at " + getTime(item.pickup_dt)
        holder.deliveredDate.text="Delivery Date : "+getDate(item.pickup_dt) + "at " + getTime(item.pickup_dt)
        holder.price.text = "Total Amount : " + Integer.valueOf( item.total_cost)/100
        holder.arrowButton.setOnClickListener {
            listener.onArrowClicked(position)
        }
    }
    private fun getDate(string: String?):String{
        if(string==null){
            return ""
        }
        return string.subSequence(0,4).toString()
    }
    private fun getTime(string: String?):String{
        if(string==null) {
            return ""
        }
        return string.subSequence(6,15).toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View, mListener: ClickListener):RecyclerView.ViewHolder(view),View.OnClickListener {
        val arrowButton:ImageView=view.findViewById(R.id.arrow_button)
        val indexTv:TextView=view.findViewById(R.id.index)
        val orderId: TextView=view.findViewById(R.id.order_id)
        val deliveredDate:TextView = view.findViewById(R.id.delivered_date)
        val pickedDate:TextView = view.findViewById(R.id.picked_date)
        val price:TextView=view.findViewById(R.id.price_tv)
        init {

            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClicked(adapterPosition)
            }
        }



    }
    interface ClickListener {

        fun onArrowClicked(position: Int)
        fun onItemClicked(position: Int)

    }


}