package com.example.task6_v1.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.task6_v1.R

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpBtn = findViewById<Button>(R.id.btnSignUp)

        signUpBtn.setOnClickListener {
            val edtName = findViewById<EditText>(R.id.userNameSignUp)
            val edtEmail = findViewById<EditText>(R.id.userEmailSignup)
            val edtPwd = findViewById<EditText>(R.id.userPasswordSignUp)
            val edtPwdCon = findViewById<EditText>(R.id.userPasswordConfirmSignUp)

            val insertName = edtName.text.toString()
            val insertEmail = edtEmail.text.toString()
            val insertPwd = edtPwd.text.toString()
            val insertPwdCon = edtPwdCon.text.toString()


            //creating the instance of databse handler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if(insertName.trim()!="" && insertEmail.trim()!="" && insertPwd.trim()!="" && insertPwdCon.trim()!=""){

                if(insertPwd==insertPwdCon){
                    //calling add user method
                    val status = databaseHandler.addUser(UserModelClass(insertName,insertEmail,insertPwd))
                    if(status > -1){
                        Log.d(ContentValues.TAG, "SignUp: Success")
                        Toast.makeText(applicationContext,"SignUp Success", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, SignIn::class.java))
                        finish()
                    }
                }
                else
                {
                    Toast.makeText(applicationContext,"password must be same", Toast.LENGTH_LONG).show()
                }

            }
            else{
                Toast.makeText(applicationContext,"data cannot be blank", Toast.LENGTH_LONG).show()
            }


        }

    }

    fun Login(view: View) {
        val i = Intent(this, SignIn::class.java)
        startActivity(i)
    }

}