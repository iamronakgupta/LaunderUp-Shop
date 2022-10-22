package com.launderup.launderupshop.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.ShopDetail
import com.launderup.launderupshop.data.ShopOwnerDetail

class ShopInformation : AppCompatActivity() {
    private lateinit var nextButton: Button
    private lateinit var shopContactNumberET: EditText
    private lateinit var ownerContactNumberET: EditText
    private lateinit var shopNameET:EditText
    private lateinit var shopAddressET:EditText
    private lateinit var ownerNameET:EditText
    private lateinit var ownerEmailET:EditText
    private lateinit var samePhoneCB: CheckBox
    private lateinit var ironingCb:CheckBox
    private lateinit var laundryCb:CheckBox
    private lateinit var dryCleanCb:CheckBox
    private lateinit var steamIronCb:CheckBox
    private lateinit var express:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_information)
        nextButton=findViewById(R.id.nextBtn)
        shopContactNumberET=findViewById(R.id.shop_contact_et)
        ownerContactNumberET=findViewById(R.id.owner_contact_et)
        samePhoneCB=findViewById(R.id.same_as_shop_rb)
        shopNameET=findViewById(R.id.shop_name_et)
        shopAddressET=findViewById(R.id.shop_address_et)
        ownerNameET=findViewById(R.id.owner_name_et)
        ownerEmailET=findViewById(R.id.owner_email_et)
        ironingCb=findViewById(R.id.ironing_checkbox)
        laundryCb=findViewById(R.id.laundry_checkbox)
        dryCleanCb=findViewById(R.id.dry_checkbox)
        steamIronCb=findViewById(R.id.steam_checkbox)
        express=findViewById(R.id.express_checkbox)




        samePhoneCB.setOnClickListener {
            onCheckboxClicked(it)
        }



        //event that will happen on click on next button
        nextButton.setOnClickListener {

            next()



        }
    }

    private fun next() {
        val shopName = shopNameET.text.toString()
        val shopAddress = shopAddressET.text.toString()
        val shopPhone = shopContactNumberET.text.toString()
        val ownerName = ownerNameET.text.toString()
        val ownerPhone = ownerContactNumberET.text.toString()
        val ownerEmail = ownerEmailET.text.toString()
        var service = ""


        if(shopName=="null"){
            val intent = Intent(this, ShopTypeClothsActivity::class.java)
            startActivity(intent)
        }

        if(shopName.isEmpty()){
            Toast.makeText(this,"Shop Name is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(shopPhone.isEmpty()){
            Toast.makeText(this,"Shop Phone Number is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(shopAddress.isEmpty()){
            Toast.makeText(this,"Shop Address is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(ownerName.isEmpty()){
            Toast.makeText(this,"Owner Name is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(ownerPhone.isEmpty()){
            Toast.makeText(this,"Owner Phone is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(ownerEmail.isEmpty()){
            Toast.makeText(this,"Owner Email is Empty",Toast.LENGTH_SHORT).show()
            return
        }else if(shopPhone.length!=10){
            Toast.makeText(this,"Shop Phone is not Valid",Toast.LENGTH_SHORT).show()
            return
        }else if(ownerPhone.length!=10){
            Toast.makeText(this,"Owner Phone is not Valid",Toast.LENGTH_SHORT).show()
            return
        }


        if(ironingCb.isChecked){
            service += "ironing"
        }
        if(laundryCb.isChecked){
            service+= ",laundry"
        }
        if(dryCleanCb.isChecked){
            service+=",dry_cleaning"
        }
        if(steamIronCb.isChecked){
            service+=",steam_ironing"
        }

        if (service.isEmpty()){
            Toast.makeText(this,"Enter Services",Toast.LENGTH_SHORT).show()
            return
        }


        ShopOwnerDetail.owner_name=ownerName
        ShopOwnerDetail.owner_address=ownerEmail
        ShopOwnerDetail.owner_phone=ownerPhone

        ShopDetail.services_available=service
        ShopDetail.shop_address=shopAddress
        ShopDetail.shop_name=shopName
        ShopDetail.shop_phone_no=shopPhone

        val intent = Intent(this, ShopTimingsActivity::class.java)


        startActivity(intent)



    }

    private fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.same_as_shop_rb -> {
                    if (checked) {
                        ownerContactNumberET.text = shopContactNumberET.editableText
                    } else {
                        ownerContactNumberET.text = null
                    }
                }
            }
        }
    }
}