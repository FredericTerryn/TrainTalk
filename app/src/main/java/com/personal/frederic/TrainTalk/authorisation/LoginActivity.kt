package com.personal.frederic.TrainTalk.authorisation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

import com.google.firebase.auth.FirebaseAuth

import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.MainActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //login code starts
        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()


            Log.d("Login", "email: $email password: $password")
            Log.d("Login", "Attempt login with email/pw: $email/***")

            //actual login to firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                //login succeeded
                .addOnCompleteListener {
                    if (it.isSuccessful) return@addOnCompleteListener
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    //login failed
                }.addOnFailureListener {
                    Log.d("LoginActivity", "Failed to login: ${it.message}")
                    Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
        }
        //login code ended

        //no login yet, register instead
        back_to_register_textview.setOnClickListener {
            finish()
        }
    }
}
