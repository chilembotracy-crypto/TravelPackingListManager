package com.tracy.travelpackaginglistmanager



object PackingData {



    const val MAX_ITEMS = 100



    val itemNames = arrayOfNulls<String>(MAX_ITEMS)

    val categories = arrayOfNulls<String>(MAX_ITEMS)

    val quantities = IntArray(MAX_ITEMS)

    val comments = arrayOfNulls<String>(MAX_ITEMS)



    var itemCount = 0



    fun addItem(

        name: String,

        category: String,

        quantity: Int,

        comment: String

    ): Boolean {



        if (itemCount >= MAX_ITEMS) {

            return false

        }



        itemNames[itemCount] = name

        categories[itemCount] = category

        quantities[itemCount] = quantity

        comments[itemCount] = comment



        itemCount++



        return true

    }

}