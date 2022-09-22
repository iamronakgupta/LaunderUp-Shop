package com.launderup.launderupshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.launderup.launderupshop.R





class OrderFragment : Fragment(R.layout.fragment_order) {

    lateinit var activeOrder:Button
    lateinit var pickedOrder:Button
    lateinit var completedOrder:Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activeOrder = view.findViewById(R.id.activeOrder)
        pickedOrder = view.findViewById(R.id.pickedUpOrder)
        completedOrder = view.findViewById(R.id.completedOrder)
        if(activeOrder.isEnabled){

        }

        activeOrder.setOnClickListener {
            completedOrder.isEnabled = false
            activeOrder.isEnabled = true
            pickedOrder.isEnabled = false
        }
        pickedOrder.setOnClickListener {
            completedOrder.isEnabled = false
            activeOrder.isEnabled = false
            pickedOrder.isEnabled = true
        }
        completedOrder.setOnClickListener {
            completedOrder.isEnabled = true
            activeOrder.isEnabled = false
            pickedOrder.isEnabled = false
        }


    }



}