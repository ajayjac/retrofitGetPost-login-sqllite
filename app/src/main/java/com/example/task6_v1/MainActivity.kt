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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task6_v1.Api.ProductsApi
import com.example.task6_v1.Api.RetrofitHelper
import com.example.task6_v1.dataclass.ResponceProduct
import com.example.task6_v1.ui.SignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var pressedTime: Long = 0
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    private var productList: ArrayList<ResponceProduct> = ArrayList()

    private lateinit var recyclerView: RecyclerView
//    private  lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(
            GsonConverterFactory.create()).build()

        val productsApi = RetrofitHelper.getInstance().create(ProductsApi::class.java)

        //lauching a new couroutine
        GlobalScope.launch {
            val result = productsApi.getProducts()
            if (result!=null){
                Log.d("Test","product: success" +result.toString())

               // Log.d("type : ", "${result::class.simpleName}")
            }
        }




       //floating button id
       val fab: View = findViewById(R.id.fab)
       //floating button action
       fab.setOnClickListener { view ->

       }

    }
    private fun init(){
        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        productList = ArrayList()

/*
        addDataToList()
*/

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

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