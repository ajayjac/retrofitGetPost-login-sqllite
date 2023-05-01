package com.example.task6_v1

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task6_v1.Api.ProductsApi
import com.example.task6_v1.Api.RetrofitHelper
import com.example.task6_v1.dataclass.ResponceProduct
import com.example.task6_v1.ui.Form
import com.example.task6_v1.ui.SignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections.addAll

class MainActivity : AppCompatActivity() {

    private var pressedTime: Long = 0
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    lateinit var rvproduct: RecyclerView
    var productList: ArrayList<ResponceProduct> = ArrayList()

    lateinit var productadapter:ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvproduct = findViewById(R.id.rv)
        rvproduct.layoutManager = GridLayoutManager(this, 2)
        getproducts()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(
            GsonConverterFactory.create()).build()


       //floating button id
       val fab: View = findViewById(R.id.fab)
       //floating button action
       fab.setOnClickListener { view ->

           val i = Intent(this, Form::class.java)
           startActivity(i)

       }

    }

    override fun onResume() {
        super.onResume()
        getproducts()
    }

    private fun getproducts() {
        val productsApi = RetrofitHelper.getInstance().create(ProductsApi::class.java)

        //lauching a new couroutine
        GlobalScope.launch(Dispatchers.Main) {
            val result = productsApi.getProducts()
            val progress_home = findViewById<ProgressBar>(R.id.progress_home)
            if (result!=null){

                rvproduct.visibility = View.VISIBLE
                productList.clear()

                productList?.addAll(result)

                init()

/*
                Log.d("Test","productList: success" +productList.toString())

                Log.d("Test","result : success" +result.toString())*/


            }
            else{
                Toast.makeText(this@MainActivity, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
            progress_home.visibility = View.GONE
        }

    }

    private fun init(){


        Log.d("Size", "init: "+productList.size)

        productadapter = ProductAdapter(this,productList)
        rvproduct.adapter = productadapter

    }
   /* private fun addDataToList(){

        productList.add(ResponceProduct("", "10"))
        productList.add(ResponceProduct("", "12"))
        productList.add(ResponceProduct("", "13"))
        productList.add(ResponceProduct("", "14"))
        productList.add(ResponceProduct("", "15"))

    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.app_bar_exit ->{
                //exitProcess(-1)

                preferences = getSharedPreferences("countSharedPref", MODE_PRIVATE )
                editor = preferences.edit()


                //dialogue builder
                val dialogBuilder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.exit_dialoge, null)
                dialogBuilder.setView(dialogView)

                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Are you sure want to Logout?")
                //positive
                dialogBuilder.setPositiveButton("Logout", DialogInterface.OnClickListener { _, _ ->

                    editor.clear()
                    editor.commit()

                    Log.d(ContentValues.TAG, "Exit: success")
                    Toast.makeText(applicationContext," User Logged Out", Toast.LENGTH_LONG).show()

                    startActivity(Intent(this, SignIn::class.java))

                    finish()

                })
                //negative
                dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                val b = dialogBuilder.create()
                b.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)


        } else {
            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()


    }

}