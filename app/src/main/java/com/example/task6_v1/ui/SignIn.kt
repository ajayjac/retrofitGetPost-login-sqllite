package com.example.task6_v1.ui

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.task6_v1.MainActivity
import com.example.task6_v1.R


class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val inputEmail = findViewById<EditText>(R.id.userEmailSignIn)
        val inputPassword = findViewById<EditText>(R.id.userPasswordSignIn)

        var helper = DatabaseHandler(applicationContext)
        var db = helper.readableDatabase

        val pref: SharedPreferences = getSharedPreferences("countSharedPref", MODE_PRIVATE)
        val editor = pref.edit()

        if(pref.contains("email")){

            startActivity(Intent(this, MainActivity::class.java))


        }

        else {

            btnLogin.setOnClickListener {
                var args = listOf<String>(inputEmail.text.toString(),
                    inputPassword.text.toString()).toTypedArray()
                var rs =
                    db.rawQuery("SELECT * FROM UserTable WHERE email = ? AND password = ?", args)
                if (rs.moveToNext()) {

                    editor.putString("email", inputEmail.text.toString())
                    editor.putString("password", inputPassword.text.toString())
                    editor.commit()

                    Toast.makeText(applicationContext, "Welcome User", Toast.LENGTH_SHORT).show()

                    inputEmail.text.clear()
                    inputPassword.text.clear()

                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

    }



    fun SignUp(view: View) {
        val i = Intent(this, SignUp::class.java)
        startActivity(i)
    }


    override fun onBackPressed() {

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.exit_dialoge, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder.setTitle("Alert")
        dialogBuilder.setMessage("Are you sure want to exit?")
        dialogBuilder.setPositiveButton("Exit", DialogInterface.OnClickListener { _, _ ->

            Log.d(ContentValues.TAG, "Exit: success")
            Toast.makeText(applicationContext,"you exited from app", Toast.LENGTH_LONG).show()

            moveTaskToBack(true)


        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val b = dialogBuilder.create()
        b.show()


    }
}