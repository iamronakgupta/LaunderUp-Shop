package com.launderup.launderupshop.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.launderup.launderupshop.R
import com.launderup.launderupshop.ui.adapter.*
import com.launderup.launderupshop.ui.view.NewOrderActivity
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_order, container, false)

        val orderChipGroup:ChipGroup=root.findViewById(R.id.order_chip_group)
        val checkChipId=orderChipGroup.checkedChipId

        val chip1:Chip=root.findViewById(R.id.chip_1)
        val chip2:Chip=root.findViewById(R.id.chip_2)
        val chip3:Chip=root.findViewById(R.id.chip_3)
        val chip4:Chip=root.findViewById(R.id.chip_4)

        val arrayList : ArrayList<String> = getItemsList()
        val recyclerView: RecyclerView =root.findViewById(R.id.order_rv)

        chip1.setOnClickListener {
            val activeOrderAdapter = context?.let { ActiveOrderAdapter(it,arrayList) }
            recyclerView.layoutManager= LinearLayoutManager(context)
            recyclerView.adapter=activeOrderAdapter

            activeOrderAdapter!!.setOnItemClickListener(object : ActiveOrderAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
                }
            })
        }

         chip2.setOnClickListener {
             val acceptedOrderAdapter = context?.let { AcceptedOrderAdapter(it,arrayList) }
             recyclerView.layoutManager= LinearLayoutManager(context)
             recyclerView.adapter=acceptedOrderAdapter

             acceptedOrderAdapter!!.setOnItemClickListener(object : AcceptedOrderAdapter.OnItemClickListener{
                 override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
                 }
             })
         }

        chip3.setOnClickListener {
            val pickedOrderAdapter = context?.let { PickedOrderAdapter(it,arrayList) }
            recyclerView.layoutManager= LinearLayoutManager(context)
            recyclerView.adapter=pickedOrderAdapter

            pickedOrderAdapter!!.setOnItemClickListener(object : PickedOrderAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
                }
            })
        }

//        chip4.setOnClickListener {
//            val acceptedOrderAdapter = context?.let { AcceptedOrderAdapter(it,arrayList) }
//            recyclerView.layoutManager= LinearLayoutManager(context)
//            recyclerView.adapter=acceptedOrderAdapter
//
//            acceptedOrderAdapter!!.setOnItemClickListener(object : AcceptedOrderAdapter.OnItemClickListener{
//                override fun onItemClick(position: Int) {
////                val intent=Intent(applicationContext,ClothesListActivity::class.java)
////                startActivity(intent)
//                }
//            })
//
//        }
        return root
    }

    private fun getItemsList(): ArrayList<String> {
        val list=ArrayList<String>()
        for(i in 1..10){
            list.add("Order $i")
        }
        return list
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}