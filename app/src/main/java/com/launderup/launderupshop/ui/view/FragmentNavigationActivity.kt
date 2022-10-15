package com.launderup.launderupshop.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.launderup.launderupshop.R
import com.launderup.launderupshop.ui.fragments.InventoryFragment
import com.launderup.launderupshop.ui.fragments.OrderFragment
import com.launderup.launderupshop.ui.fragments.ProfileFragment
import com.launderup.launderupshop.ui.fragments.StatsFragment

class FragmentNavigationActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var expressBtn:SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_navigation)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        expressBtn = findViewById(R.id.express_switch_btn)

        val newOrder: TextView =findViewById(R.id.new_order_tv)

        newOrder.setOnClickListener{
            val intent= Intent(this,NewOrderActivity::class.java)
            startActivity(intent)
        }

        expressBtn.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked)
                Toast.makeText(this,"Switch Selected",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Switch Deselected",Toast.LENGTH_SHORT).show()
        }

        replaceFragment(OrderFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.orderFragment->replaceFragment(OrderFragment())
                R.id.statsFragment->replaceFragment(StatsFragment())
                R.id.inventoryFragment->replaceFragment(InventoryFragment())
                R.id.profileFragment->replaceFragment(ProfileFragment())

                else->{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_navigation_activity,fragment).commit()
    }
}