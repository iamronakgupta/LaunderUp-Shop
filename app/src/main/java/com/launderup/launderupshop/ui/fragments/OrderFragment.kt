package com.launderup.launderupshop.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.OrdersResponse
import com.launderup.launderupshop.data.models.ShopDetailResponse
import com.launderup.launderupshop.data.models.SingleOrderResponse
import com.launderup.launderupshop.repository.ShopRepository
import com.launderup.launderupshop.ui.adapter.*
import com.launderup.launderupshop.ui.view.NewOrderActivity
import com.launderup.launderupshop.ui.view.OrderActivity
import com.launderup.launderupshop.utils.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList



class OrderFragment : Fragment(),ActiveOrderAdapter.ClickListener {
    private var chipSelected = 1
    private lateinit var arrayList:ArrayList<SingleOrderResponse>
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    private var shid:String="d"
    private var shopDetail:ShopDetailResponse? = null
    private lateinit var noOrderTv:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getOrdersList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_order, container, false)

        val orderChipGroup:ChipGroup=root.findViewById(R.id.order_chip_group)
        progressBar = root.findViewById(R.id.progress_bar)
        val checkChipId=orderChipGroup.checkedChipId
        noOrderTv=root.findViewById(R.id.no_order_tv)

        val chip1:Chip=root.findViewById(R.id.chip_1)
        val chip2:Chip=root.findViewById(R.id.chip_2)
        val chip3:Chip=root.findViewById(R.id.chip_3)
        val chip4:Chip=root.findViewById(R.id.chip_4)
        chip1.setChipBackgroundColorResource(R.color.blue_5)

//        if(shopDetail==null){
//           shopDetail = context?.let { ShopRepository().getShopDetail(it,"ShopDetail") }
//        }


        sharedPreferences= context?.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)!!
        shid = sharedPreferences.getString("shid",null).toString()

        recyclerView=root.findViewById(R.id.order_rv)
        recyclerView.layoutManager= LinearLayoutManager(context)
        arrayList= ArrayList<SingleOrderResponse>()

        getOrdersList()


        chip1.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            chip1.setChipBackgroundColorResource(R.color.blue_5)
            chip2.setChipBackgroundColorResource(R.color.blue_4)
            chip3.setChipBackgroundColorResource(R.color.blue_4)
            chip4.setChipBackgroundColorResource(R.color.blue_4)

            chipSelected=1
            getOrdersList()


        }

         chip2.setOnClickListener {

             progressBar.visibility = View.VISIBLE
             chip1.setChipBackgroundColorResource(R.color.blue_4)
             chip2.setChipBackgroundColorResource(R.color.blue_5)
             chip3.setChipBackgroundColorResource(R.color.blue_4)
             chip4.setChipBackgroundColorResource(R.color.blue_4)

             chipSelected=2
             getOrdersList()


         }

        chip3.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            chip1.setChipBackgroundColorResource(R.color.blue_4)
            chip2.setChipBackgroundColorResource(R.color.blue_4)
            chip3.setChipBackgroundColorResource(R.color.blue_5)
            chip4.setChipBackgroundColorResource(R.color.blue_4)

            chipSelected=3
            getOrdersList()

        }

        chip4.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            chip1.setChipBackgroundColorResource(R.color.blue_4)
            chip2.setChipBackgroundColorResource(R.color.blue_4)
            chip3.setChipBackgroundColorResource(R.color.blue_4)
            chip4.setChipBackgroundColorResource(R.color.blue_5)

            chipSelected=4
            getOrdersList()



        }
        return root
    }




    private fun getOrdersList(){
        var status = ""
        if(chipSelected==1){
            status = "all"
        }else if(chipSelected==2){
            status="accepted"
        }else if(chipSelected==3){
            status="picked"
        }else if(chipSelected==4){
            status="completed"
        }

        val order = HerokuInstance.herokuapi.getShopOrder(shid,status,1)
        order.enqueue(object:Callback<OrdersResponse> {
            override fun onResponse(call: Call<OrdersResponse>, response: Response<OrdersResponse>) {

                if(response.code()==200){
                    //Toast.makeText(context,"Order Fetched",Toast.LENGTH_LONG).show()
                    Log.i("Response Orders",response.body().toString())
                    arrayList = response.body()?.data!!
                    if(arrayList.isEmpty()){
                        noOrderTv.visibility =View.VISIBLE
                    }else{
                        noOrderTv.visibility=View.INVISIBLE
                    }

                    recyclerView.adapter= context?.let { ActiveOrderAdapter(it,arrayList,this@OrderFragment) }
                }else{
                    noOrderTv.visibility =View.VISIBLE
                    //Toast.makeText(context,"Order Not Fetched",Toast.LENGTH_LONG).show()
                }
                progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
                noOrderTv.visibility =View.VISIBLE
                progressBar.visibility = View.INVISIBLE
               //Toast.makeText(context,"Api Failed",Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onArrowClicked(position: Int) {
        Toast.makeText(context,"Arrow Clicked",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, OrderActivity::class.java)
        intent.putExtra("order",arrayList.get(position))
        startActivity(intent)
    }

    override fun onItemClicked(position: Int) {
       Toast.makeText(context,"Order",Toast.LENGTH_SHORT).show()
    }

}