package com.launderup.launderupshop.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.SingleOrderResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity() {
    private lateinit var nameTv:TextView
    private lateinit var phoneTv:TextView
    private lateinit var addressTv:TextView
    private lateinit var orderIdTv:TextView
    private lateinit var datePickupTv:TextView
    private lateinit var timePickupTv:TextView
    private lateinit var dateDeliveryTv:TextView
    private lateinit var timeDeliveryTv:TextView
    private lateinit var clothListRv:RecyclerView
    private lateinit var priceTv:TextView
    private lateinit var button: Button

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        nameTv = findViewById(R.id.name_change)
        phoneTv = findViewById(R.id.phone_change)
        addressTv = findViewById(R.id.address_change)
        orderIdTv = findViewById(R.id.order_id_change)
        dateDeliveryTv = findViewById(R.id.date_delivery_change)
        datePickupTv = findViewById(R.id.date_pickup_change)
        timePickupTv = findViewById(R.id.time_pickup_change)
        timeDeliveryTv = findViewById(R.id.time_delivery_change)
        clothListRv = findViewById(R.id.cloth_list_rv)
        priceTv = findViewById(R.id.price_change)
        button = findViewById(R.id.nextBtn)

        val data = intent.getParcelableExtra<SingleOrderResponse>("order")
        nameTv.text =""
        phoneTv.text = ""
        addressTv.text = data?.address
        orderIdTv.text = data?.order_id
        datePickupTv.text = data?.pickup_dt?.let { getDate(it) }
        timePickupTv.text = data?.pickup_dt?.let { getTime(it) }
        dateDeliveryTv.text = data?.delivery_dt?.let { getDate(it) }
        timeDeliveryTv.text = data?.delivery_dt?.let { getTime(it) }
        priceTv.text = data?.total_cost


        button.setOnClickListener {
            data?.let { it1 -> statusApiCall(button.text.toString().lowercase(), it1) }
        }

        if(data?.status?.lowercase()=="placed"){
            button.text = "accepted"

        }else if(data?.status?.lowercase()=="accepted"){
            button.text = "picked"
        }else if(data?.status?.lowercase()=="picked"){
            button.text = "completed"

        }else{
           button.visibility = View.INVISIBLE
        }

    }

    private fun getDate(string: String):String{
        return string.subSequence(0,4).toString()
    }
    private fun getTime(string: String):String{
        return string.subSequence(6,15).toString()
    }

    private fun statusApiCall(status:String,order: SingleOrderResponse){

        val statusChange = order.shid?.let { order.uid?.let { it1 ->
            order.order_id?.let { it2 ->
                HerokuInstance.herokuapi.updateOrder(it,
                    it1, it2,status)
            }
        } }
        statusChange?.enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if(response.code()==200){
                    Toast.makeText(this@OrderActivity,"Status Changed", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@OrderActivity,"Status Not Changed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Toast.makeText(this@OrderActivity,"Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }


}