package com.launderup.launderupshop.utils

import com.launderup.launderupshop.data.models.ClothEntity

class ClothData {


    companion object {
        private val men = ArrayList<ClothEntity>()
        private val women = ArrayList<ClothEntity>()
        private val kids = ArrayList<ClothEntity>()

        fun menCloths():ArrayList<ClothEntity>{
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

            return men

        }

        fun womenCloths():ArrayList<ClothEntity>{
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

            return women
        }


        fun kidsCloth():ArrayList<ClothEntity>{
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
            return kids
        }



    }

}