package com.launderup.launderupshop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.api.HerokuInstance
import com.launderup.launderupshop.data.models.StatsResponse
import com.launderup.launderupshop.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatsFragment : Fragment() {
    private lateinit var totalorder:TextView
    private lateinit var totalEarning:TextView
    private lateinit var weekChip:Chip
    private lateinit var monthChip:Chip
    private lateinit var yearChip:Chip
    private lateinit var progressBar: ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    private var shid:String="d"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val root = inflater.inflate(R.layout.fragment_stats, container, false)

        totalorder = root.findViewById(R.id.total_order_change)
        totalEarning = root.findViewById(R.id.total_earning_change)
        weekChip = root.findViewById(R.id.this_week)
        monthChip = root.findViewById(R.id.this_month)
        yearChip = root.findViewById(R.id.this_year)
        progressBar = root.findViewById(R.id.progress_bar)

        sharedPreferences= context?.getSharedPreferences(Resource.sharedPrefFile, Context.MODE_PRIVATE)!!
        shid = sharedPreferences.getString("shid",null).toString()


        apiCall("week")
        weekChip.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            apiCall("week")
        }


        monthChip.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            apiCall("month")

        }


        yearChip.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            apiCall("year")
        }

        return root
    }

    private fun apiCall(type:String) {

        val stats = HerokuInstance.herokuapi.stats(shid, type)

        stats.enqueue(object:Callback<StatsResponse>{
            override fun onResponse(call: Call<StatsResponse>, response: Response<StatsResponse>) {

                progressBar.visibility = View.INVISIBLE
                if(response.code()==200){
                    val body = response.body()
                    totalorder.text = body?.order.toString()
                    totalEarning.text = body?.earning.toString()

                }else{
                    Toast.makeText(context,"Something Wrong : )",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<StatsResponse>, t: Throwable) {
                Toast.makeText(context,"Fail ...",Toast.LENGTH_SHORT).show()
            }

        })


    }


}