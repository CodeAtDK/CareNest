package com.example.carenest

import User
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.carenest.HealthMain.HealthMainActivity
import com.example.carenest.databinding.ActivitySignUpBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class Sign_Up : AppCompatActivity() {

    // Data Binding
    lateinit var binding: ActivitySignUpBinding

    // Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // This will bind the class to activity_sign_up
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        // Initialize Firebase Auth
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // Enable Firebase Realtime Database persistence
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        } catch (e: Exception) {
            Log.e(TAG, "Firebase persistence failed", e)
        }

        // Set click listener for the Sign-Up button
        binding.SignId.setOnClickListener {


            createUser()
        }
    }

    // Create a new user with email and password
    private fun createUser() {
        val email = binding.signUpEmailId.text.toString()
        val password = binding.signUpRenterPassword.text.toString()
        val role = "patient"
            // if (binding.roleSpinner.selectedItem.toString() == "Doctor") "doctor" else "patient" // Ensure proper role assignment

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user,role)
                } else {
                    // If sign in fails
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null,role)
                }
            }
    }

    // Update UI after user creation
    private fun updateUI(user: FirebaseUser?, role: String) {
        if (user != null) {
            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
            addUserToDatabase(user.uid, binding.signUpFirstName.text.toString(), user.email ?: "")
            // Redirect to another activity if needed
             val intent = Intent(this, HealthMainActivity::class.java)
             intent.putExtra("role",role)
             startActivity(intent)
        }
    }

    // Add user data to Firebase Realtime Database
    private fun addUserToDatabase(userId: String, userName: String, userEmail: String) {
        val userReference = FirebaseDatabase.getInstance().getReference("users")
        val user = User(userId, userName, userEmail,"patient")
        userReference.child(userId).setValue(user)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
       // updateUI(currentUser,"patient")
    }
}
