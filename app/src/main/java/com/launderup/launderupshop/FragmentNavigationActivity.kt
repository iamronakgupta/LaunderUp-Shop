package com.launderup.launderupshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.MenuItem
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.launderup.launderupshop.fragments.InventoryFragment
import com.launderup.launderupshop.fragments.OrderFragment
import com.launderup.launderupshop.fragments.ProfileFragment
import com.launderup.launderupshop.fragments.StatsFragment

class FragmentNavigationActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_navigation)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val orderFragment = OrderFragment()
        val inventoryFragment= InventoryFragment()
        val profileFragment = ProfileFragment()
        val statsFragment = StatsFragment()
        setCurrentFragment(orderFragment)




        underlineMenuItem(bottomNavigationView.menu.getItem(0))
        bottomNavigationView.setOnItemReselectedListener {
            removeItemsUnderline(bottomNavigationView)
            underlineMenuItem(it)
            when(it.itemId){
                R.id.orderFragment->setCurrentFragment(orderFragment)
                R.id.inventoryFragment->setCurrentFragment(inventoryFragment)
                R.id.profileFragment->setCurrentFragment(profileFragment)
                R.id.statsFragment->setCurrentFragment(statsFragment)
            }


        }

    }



    private fun removeItemsUnderline(bottomNavigationView: BottomNavigationView){
        for(i in bottomNavigationView.menu){

            i.setTitle(i.title.toString())
        }
    }

    private fun underlineMenuItem(item:MenuItem){
        val content = SpannableString(item.title)
        content.setSpan(UnderlineSpan(),0,content.length,0)
        item.title = content
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}