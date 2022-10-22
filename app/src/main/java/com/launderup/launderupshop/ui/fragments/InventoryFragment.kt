package com.launderup.launderupshop.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.launderup.launderupshop.R
import com.launderup.launderupshop.data.ClothEntity
import com.launderup.launderupshop.ui.adapter.ClothListAdapter
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var menClothListAdapter:ClothListAdapter
    private lateinit var womenClothListAdapter:ClothListAdapter
    private lateinit var kidsClothListAdapter:ClothListAdapter

    var men=ArrayList<ClothEntity>()
    var women=ArrayList<ClothEntity>()
    var kids = ArrayList<ClothEntity>()


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
        val root= inflater.inflate(R.layout.fragment_inventory, container, false)
        getItemsList()
        val arrayList : ArrayList<ClothEntity> = men
        val recyclerView: RecyclerView =root.findViewById(R.id.inventory_rv)
        val clothListAdapter = context?.let { ClothListAdapter(it,arrayList) }


        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=clothListAdapter

        clothListAdapter!!.setOnItemClickListener(object : ClothListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
//                val intent=Intent(applicationContext,ClothesListActivity::class.java)
//                startActivity(intent)
            }
        })
        return root
    }

    private fun getItemsList() {
        //mens cloths
        men.add(ClothEntity("Bathrobe",null,null))
        men.add(ClothEntity("Cap",null,null))
        men.add(ClothEntity("Coat",null,null))
        men.add(ClothEntity("Gentssuit",null,null))
        men.add(ClothEntity("Halfpants",null,null))
        men.add(ClothEntity("Halfsweater",null,null))
        men.add(ClothEntity("Handkerchief",null,null))
        men.add(ClothEntity("Jacket",null,null))
        men.add(ClothEntity("Jeans",null,null))
        men.add(ClothEntity("Jumpsuit",null,null))
        men.add(ClothEntity("Kurta",null,null))
        men.add(ClothEntity("Leatherjacket",null,null))
        men.add(ClothEntity("Overcoat",null,null))
        men.add(ClothEntity("Shawl",null,null))
        men.add(ClothEntity("Sherwani",null,null))
        men.add(ClothEntity("Shirt",null,null))
        men.add(ClothEntity("Shoes",null,null))
        men.add(ClothEntity("Shorts",null,null))
        men.add(ClothEntity("Socks",null,null))
        men.add(ClothEntity("Sweater",null,null))
        men.add(ClothEntity("Sweatshirt",null,null))
        men.add(ClothEntity("Tie",null,null))
        men.add(ClothEntity("Trackpants",null,null))
        men.add(ClothEntity("Trousers",null,null))
        men.add(ClothEntity("Tshirt",null,null))
        men.add(ClothEntity("Undergarments",null,null))
        men.add(ClothEntity("Waistcoat",null,null))


        //womens cloths
        women.add(ClothEntity("Bathrobe",null,null))
        women.add(ClothEntity("Blouse",null,null))
        women.add(ClothEntity("Blouse",null,null))
        women.add(ClothEntity("Cap",null,null))
        women.add(ClothEntity("CholilehengaDupatta",null,null))
        women.add(ClothEntity("Coat",null,null))
        women.add(ClothEntity("Dupatta",null,null))
        women.add(ClothEntity("Gowndress",null,null))
        women.add(ClothEntity("Halfpants",null,null))
        women.add(ClothEntity("Halfsweater",null,null))
        women.add(ClothEntity("Handkerchief",null,null))
        women.add(ClothEntity("Jacket",null,null))
        women.add(ClothEntity("Jeans",null,null))
        women.add(ClothEntity("Jumpsuit",null,null))
        women.add(ClothEntity("Kurti",null,null))
        women.add(ClothEntity("Ladiessuit",null,null))
        women.add(ClothEntity("Leatherjacket",null,null))
        women.add(ClothEntity("Nighty",null,null))
        women.add(ClothEntity("Overcoat",null,null))
        women.add(ClothEntity("Purse",null,null))
        women.add(ClothEntity("Saree",null,null))
        women.add(ClothEntity("Shawl",null,null))
        women.add(ClothEntity("Shirt",null,null))
        women.add(ClothEntity("Shoes",null,null))
        women.add(ClothEntity("Shorts",null,null))
        women.add(ClothEntity("Skirt",null,null))
        women.add(ClothEntity("Socks",null,null))
        women.add(ClothEntity("Sweater",null,null))
        women.add(ClothEntity("Sweatshirt",null,null))
        women.add(ClothEntity("Tie",null,null))
        women.add(ClothEntity("Trackpants",null,null))
        women.add(ClothEntity("Trousers",null,null))
        women.add(ClothEntity("Tshirt",null,null))
        women.add(ClothEntity("Undergarments",null,null))
        women.add(ClothEntity("Waistcoat",null,null))


        //kids cloths
        kids.add(ClothEntity("Bathrobe",null,null))
        kids.add(ClothEntity("Blouse",null,null))
        kids.add(ClothEntity("Cap",null,null))
        kids.add(ClothEntity("CholilehengaDupatta",null,null))
        kids.add(ClothEntity("Coat",null,null))
        kids.add(ClothEntity("Dupatta",null,null))
        kids.add(ClothEntity("Gentssuit",null,null))
        kids.add(ClothEntity("Gowndress",null,null))
        kids.add(ClothEntity("Halfpants",null,null))
        kids.add(ClothEntity("Halfsweater",null,null))
        kids.add(ClothEntity("Handkerchief",null,null))
        kids.add(ClothEntity("Jacket",null,null))
        kids.add(ClothEntity("Jeans",null,null))
        kids.add(ClothEntity("Jumpsuit",null,null))
        kids.add(ClothEntity("Kurta",null,null))
        kids.add(ClothEntity("Kurti",null,null))
        kids.add(ClothEntity("Ladiessuit",null,null))
        kids.add(ClothEntity("Leatherjacket",null,null))
        kids.add(ClothEntity("Nighty",null,null))
        kids.add(ClothEntity("Overcoat",null,null))
        kids.add(ClothEntity("Purse",null,null))
        kids.add(ClothEntity("Saree",null,null))
        kids.add(ClothEntity("Shawl",null,null))
        kids.add(ClothEntity("Sherwani",null,null))
        kids.add(ClothEntity("Shirt",null,null))
        kids.add(ClothEntity("Shoes",null,null))
        kids.add(ClothEntity("Shorts",null,null))
        kids.add(ClothEntity("Skirt",null,null))
        kids.add(ClothEntity("Socks",null,null))
        kids.add(ClothEntity("Sweater",null,null))
        kids.add(ClothEntity("Sweatshirt",null,null))
        kids.add(ClothEntity("Tie",null,null))
        kids.add(ClothEntity("Trackpants",null,null))
        kids.add(ClothEntity("Trousers",null,null))
        kids.add(ClothEntity("Tshirt",null,null))
        kids.add(ClothEntity("Undergarments",null,null))
        kids.add(ClothEntity("Waistcoat",null,null))


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InventoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InventoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}