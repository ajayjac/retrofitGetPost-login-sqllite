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
import androidx.recyclerview.widget.RecyclerView
import com.example.task6_v1.ui.SignIn

class MainActivity : AppCompatActivity() {

    private var pressedTime: Long = 0
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

   /* private lateinit var recyclerView: RecyclerView*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       //floating button id
       val fab: View = findViewById(R.id.fab)


       //floating button action
       fab.setOnClickListener { view ->
           /*val i = Intent(this, Form::class.java)
           startActivity(i)*/

           /* val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.insert_dialog, null)
            dialogBuilder.setView(dialogView)


            val edtName = dialogView.findViewById(R.id.updateName) as EditText
            val edtPhone = dialogView.findViewById(R.id.updatePhone) as EditText
            val edtAddress = dialogView.findViewById(R.id.updateAddress) as EditText
            val edtLocation = dialogView.findViewById(R.id.updateLocation) as EditText


            dialogBuilder.setTitle("Insert Record")
            dialogBuilder.setMessage("Enter data below")
            dialogBuilder.setPositiveButton("Insert", DialogInterface.OnClickListener { _, _ ->


                val updateName = edtName.text.toString()
                val updatePhone = edtPhone.text.toString()
                val updateAddress = edtAddress.text.toString()
                val updateLocation = edtLocation.text.toString()
                //creating the instance of DatabaseHandler class
                val databaseHandler: DatabaseHandler = DatabaseHandler(this)
                if(updatePhone.trim()!="" && updateName.trim()!="" && updateAddress.trim()!="" && updateLocation.trim()!=""){
                    //calling the updateEmployee method of DatabaseHandler class to update record
                    val status = databaseHandler.addEmployee(EmpModelClass(updateName, Integer.parseInt(updatePhone), updateAddress, updateLocation ))
                    if(status > -1){
                        Log.d(TAG, "Data: Inserted")
                        Toast.makeText(applicationContext,"record inserted", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"data cannot be blank", Toast.LENGTH_LONG).show()
                }

            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                //pass
            })
            val b = dialogBuilder.create()
            b.show()*/
       }

    }

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