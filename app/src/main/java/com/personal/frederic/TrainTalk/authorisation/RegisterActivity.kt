package com.personal.frederic.TrainTalk.authorisation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

/**
 * Activity where a new user can register.
 */
class RegisterActivity : AppCompatActivity() {

    /**
     * Uri for profile picture. See OnActivityResult
     */
    var selectedPhotoUri: Uri? = null

    /**
     * On creation of this activity, user can do 3 actions, listeners to this actions are instanciated here
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        register_button_register.setOnClickListener {
            performRegister()
        }

        already_have_an_account_text_view.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        selectphoto_button_register.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            startActivityForResult(intent, 0)
            Log.d("RegisterActivity", "Photo selector")
        }
    }


    /**
     * Handles the result when a photo is picked.
     *
     * Reads the data-element that contains the uri of the picture. ImageView Takes it as a Bitmap.
     * Button opacity is set to zero: you don't see the button, but the picture, and when you click it, you can choose a new picture.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "Photo was selected")

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            selectphoto_imageview_register.setImageBitmap(bitmap)
            selectphoto_button_register.alpha = 0f

        }
    }

    /**
     * Firebase registry
     *
     * First reads the inserted userinfo.
     * Control empty fields.
     * Actual register to Firebase with standard method. With Listeners to do actions according to if registry worked or not.
     * It :  taskresult witch contains the info
     */
    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterActivity", "Email is:  $email")
        Log.d("RegisterActivity", "Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

            if (!it.isSuccessful) return@addOnCompleteListener

            Log.d("RegisterActivity", "Succesfullly created user with uid: ${it.result?.user?.uid}")

            uploadImageToFirebaseStorage()

            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
    }

    /**
     * Uploads images.
     *
     * Makes a unique id for eacht picture to save in the database.
     */
    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        //make a random unique id for the images
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!).addOnSuccessListener {
            Log.d("RegisterActivity", "succesfully uploaded image")

            ref.downloadUrl.addOnSuccessListener {
                Log.d("RegisterActivity", "Succesfully downloaded url")
                saveUserToFirebaseDatabase(it.toString())
            }
        }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Something went wrong ")
            }
    }

    /**
     * Saves the user to the firebase Database.
     */
    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, username_edittext_register.text.toString(), profileImageUrl)

        ref.setValue(user).addOnSuccessListener {
            Log.d("RegisterActivity", "Finally we save the user to Firebase Database")
        }

    }
}

/**
 * Separate class which serves as model for a User.
 */
class User(val uid: String, val username: String, val profileImageUrl: String)