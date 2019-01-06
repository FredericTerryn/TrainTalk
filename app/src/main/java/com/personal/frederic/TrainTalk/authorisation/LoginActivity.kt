package com.personal.frederic.TrainTalk.authorisation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.mainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("Login", "email: $email password: $password" )

            Log.d("Login", "Attempt login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful) return@addOnCompleteListener
                    if(it.isSuccessful)
                   {
                        val intent = Intent(this, mainActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener {
                    Log.d("LoginActivity", "Failed to login: ${it.message}")
                    Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
        }
        //   .add


        back_to_register_textview.setOnClickListener {
            finish()
        }
    }
}
