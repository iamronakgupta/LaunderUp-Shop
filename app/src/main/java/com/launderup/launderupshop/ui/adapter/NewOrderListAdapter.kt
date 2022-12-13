package com.launderup.launderupshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.models.SingleOrderResponse

class NewOrderListAdapter(val context: Context, private val items:java.util.ArrayList<SingleOrderResponse>,mlistener:ClickListener):

    RecyclerView.Adapter<NewOrderListAdapter.ViewHolder>() {

        val listener:ClickListener = mlistener





        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.new_order_cardview, parent, false),listener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item= items[position]
            holder.indexTv.text=position.toString()
            holder.orderId.text="Order Id : "+ item.order_id
            holder.pickedDate.text="Pickup Date : " + getDate(item.pickup_dt) + "at " + getTime(item.pickup_dt)
            holder.deliveredDate.text="Delivery Date : "+getDate(item.pickup_dt) + "at " + getTime(item.pickup_dt)
            holder.price.text="Total Amount : " + Integer.valueOf( item.total_cost)/100
            holder.rejectBtn.setOnClickListener {
                listener.onRejectClicked(position)
            }

            holder.acceptBtn.setOnClickListener {
                listener.onAcceptClicked(position)
            }

        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class ViewHolder(view: View, listener: ClickListener) : RecyclerView.ViewHolder(view) , View.OnClickListener {

            val indexTv:TextView=view.findViewById(R.id.index)
            val orderId: TextView=view.findViewById(R.id.order_id)
            val deliveredDate:TextView = view.findViewById(R.id.delivered_date)
            val pickedDate:TextView = view.findViewById(R.id.picked_date)
            val price:TextView=view.findViewById(R.id.price_tv)
            val acceptBtn:Button=view.findViewById(R.id.new_order_accept_btn)
            val rejectBtn:Button=view.findViewById(R.id.new_order_reject_btn)

            override fun onClick(p0: View?) {
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    listener.onItemClicked(adapterPosition)
                }

            }

            init {

                itemView.setOnClickListener(this)
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

    }

        interface ClickListener {
            fun onAcceptClicked(position: Int)
            fun onRejectClicked(position: Int)
            fun onItemClicked(position: Int)

        }
