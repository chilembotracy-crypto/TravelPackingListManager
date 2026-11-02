package com.tracy.travelpackaginglistmanager


import android.app.Activity

import android.app.AlertDialog

import android.content.Intent

import android.os.Bundle

import android.util.Log

import android.widget.Button

import android.widget.EditText

import android.widget.Toast



class MainActivity : Activity() {



    companion object {

        private const val TAG = "PackingListApp"

    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)



        val btnAddItem = findViewById<Button>(R.id.btnAddItem)

        val btnViewList = findViewById<Button>(R.id.btnViewList)

        val btnExit = findViewById<Button>(R.id.btnExit)



        btnAddItem.setOnClickListener {

            showAddItemDialog()

        }



        btnViewList.setOnClickListener {

            Log.d(TAG, "Opening packing list screen")



            val intent = Intent(

                this,

                PackingListActivity::class.java

            )



            startActivity(intent)

        }



        btnExit.setOnClickListener {

            Log.d(TAG, "User selected Exit App")



            AlertDialog.Builder(this)

                .setTitle("Exit Application")

                .setMessage("Are you sure you want to exit?")

                .setPositiveButton("Yes") { _, _ ->

                    finish()

                }

                .setNegativeButton("No", null)

                .show()

        }

    }



    private fun showAddItemDialog() {



        val inputLayout = android.widget.LinearLayout(this)



        inputLayout.orientation =

            android.widget.LinearLayout.VERTICAL



        val padding = (16 * resources.displayMetrics.density).toInt()



        inputLayout.setPadding(

            padding,

            padding,

            padding,

            padding

        )



        val etName = EditText(this)

        etName.hint = "Item name"

        inputLayout.addView(etName)



        val etCategory = EditText(this)

        etCategory.hint = "Category"

        inputLayout.addView(etCategory)



        val etQuantity = EditText(this)

        etQuantity.hint = "Quantity"

        etQuantity.inputType =

            android.text.InputType.TYPE_CLASS_NUMBER



        inputLayout.addView(etQuantity)



        val etComment = EditText(this)

        etComment.hint = "Comments"

        inputLayout.addView(etComment)



        val dialog = AlertDialog.Builder(this)

            .setTitle("Add Packing Item")

            .setView(inputLayout)

            .setPositiveButton("Save", null)

            .setNegativeButton("Cancel", null)

            .create()



        dialog.setOnShowListener {



            dialog.getButton(AlertDialog.BUTTON_POSITIVE)

                .setOnClickListener {



                    val name = etName.text.toString().trim()

                    val category =

                        etCategory.text.toString().trim()

                    val quantityText =

                        etQuantity.text.toString().trim()

                    val comment =

                        etComment.text.toString().trim()



                    val quantity = quantityText.toIntOrNull()



                    when {

                        name.isEmpty() -> {

                            etName.error = "Enter an item name"

                            etName.requestFocus()

                        }



                        category.isEmpty() -> {

                            etCategory.error = "Enter a category"

                            etCategory.requestFocus()

                        }



                        quantity == null || quantity <= 0 -> {

                            etQuantity.error =

                                "Enter a quantity greater than zero"

                            etQuantity.requestFocus()

                        }



                        else -> {



                            val added = PackingData.addItem(

                                name,

                                category,

                                quantity,

                                comment

                            )



                            if (added) {



                                Log.d(

                                    TAG,

                                    "Packing item added: $name, quantity=$quantity"

                                )



                                Toast.makeText(

                                    this,

                                    "$name added successfully",

                                    Toast.LENGTH_SHORT

                                ).show()



                                dialog.dismiss()



                            } else {



                                Log.e(

                                    TAG,

                                    "Cannot add item: packing list is full"

                                )



                                Toast.makeText(

                                    this,

                                    "Packing list is full",

                                    Toast.LENGTH_LONG

                                ).show()

                            }

                        }

                    }

                }

        }



        dialog.show()

    }

}

