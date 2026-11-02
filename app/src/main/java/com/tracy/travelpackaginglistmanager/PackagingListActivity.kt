package com.tracy.travelpackaginglistmanager


import android.app.Activity

import android.os.Bundle

import android.util.Log

import android.widget.Button

import android.widget.TextView

import android.widget.Toast



class PackingListActivity : Activity() {



    companion object {

        private const val TAG = "PackingListApp"

    }



    private lateinit var txtPackingList: TextView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_packaging_list)



        txtPackingList =

            findViewById(R.id.txtPackingList)



        val btnDisplayList =

            findViewById<Button>(R.id.btnDisplayList)



        val btnReturn =

            findViewById<Button>(R.id.btnReturn)



        btnDisplayList.setOnClickListener {

            displayPackingList()

        }



        btnReturn.setOnClickListener {

            Log.d(TAG, "Returning to main screen")

            finish()

        }



        displayPackingList()

    }



    private fun displayPackingList() {



        val count = PackingData.itemCount



        if (count == 0) {



            txtPackingList.text =

                "Your packing list is empty. Go back and add some items."



            Toast.makeText(

                this,

                "No packing items found",

                Toast.LENGTH_SHORT

            ).show()



            Log.d(TAG, "Packing list is empty")



            return

        }



        val result = StringBuilder()



        // Loop through all saved packing items.

        for (i in 0 until count) {



            result.append("Item: ")

                .append(PackingData.itemNames[i])

                .append("\n")



            result.append("Category: ")

                .append(PackingData.categories[i])

                .append("\n")



            result.append("Quantity: ")

                .append(PackingData.quantities[i])

                .append("\n")



            result.append("Comments: ")

                .append(

                    PackingData.comments[i]

                        ?.ifEmpty { "No comments" }

                        ?: "No comments"

                )

                .append("\n")



            result.append("-------------------------\n")

        }



        txtPackingList.text = result.toString()



        Log.d(

            TAG,

            "Displayed $count packing items"

        )

    }

}

