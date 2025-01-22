package com.example.carenest

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.carenest.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class Sign_In : AppCompatActivity() {

    // Data Binding
    lateinit var binding: ActivitySignInBinding
    // Firebase authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // This will bind the file with activity_sign_in
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        // ...
        // Initialize Firebase Auth
        auth = Firebase.auth

        // on click checkUser will call
        binding.SignUpButton.setOnClickListener(){

            checkUser()


        }
    }

    // Here email and password will be taken form the edittextview and store as email and password then this will send to firebase to authenticate the user is real of not.
    // Then it will call the update UI function and give parameter as activity name
    private fun checkUser() {

        val email = binding.SignInEmailId.text.toString()
        val password = binding.SignInPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {

        if(user != null){

//            val intent = Intent(this, FarmerMainActivity::class.java)
//            startActivity(intent)

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }

    }

    // this will set the value of current user
    override fun onStart() {

        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {

    }
}