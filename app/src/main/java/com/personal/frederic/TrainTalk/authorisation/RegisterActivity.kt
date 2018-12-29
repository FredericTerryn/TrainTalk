package com.personal.frederic.TrainTalk.authorisation

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.personal.frederic.TrainTalk.R
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



        register_button_register.setOnClickListener {
            performRegister()
        }

        already_have_an_account_text_view.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        selectphoto_button_register.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            //start activity for getting image from device, it will send a result, which is captured in onActivityResult (see #1)
            startActivityForResult(intent, 0)
            Log.d("RegisterActivity", "Photo selector")
        }
    }
    var selectedPhotoUri: Uri? = null

    //Here you capture the result #1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            Log.d("RegisterActivity", "Photo was selected")

         selectedPhotoUri = data.data
           val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            selectphoto_button_register.setBackgroundDrawable(bitmapDrawable)

        }
    }

    private fun performRegister(){
        val email= email_edittext_register.text.toString()
        val password = password_edittext_register.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterActivity", "Email is:  $email")
        Log.d("RegisterActivity", "Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { //it is de task-result, en daar kan je dingen mee doen.
            if (!it.isSuccessful) return@addOnCompleteListener

            //else if succesfull
            Log.d("RegisterActivity", "Succesfullly created user with uid: ${it.result?.user?.uid}")

            uploadImageToFirebaseStorage()
        }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImageToFirebaseStorage(){
        //eerst checken of er wel een photouri is
        if(selectedPhotoUri == null)return

        //make a random unique id for the images
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!).addOnSuccessListener {
            Log.d("RegisterActivity" , "succesfully uploaded image")

            ref.downloadUrl.addOnSuccessListener {
                Log.d("RegisterActivity", "Succesfully downloaded url")
                saveUserToFirebaseDatabase(it.toString())
            }
        }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Something went wrong ")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, username_edittext_register.text.toString(), profileImageUrl)

        ref.setValue(user).addOnSuccessListener {
            Log.d("RegisterActivity", "Finally we save the user to Firebase Database")
        }

    }
}

//url is de file location die uit it.tostring komt
class User(val uid: String, val username: String, val profileImageUrl: String)