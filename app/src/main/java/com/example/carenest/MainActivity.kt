package com.example.carenest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.carenest.HealthMain.HealthMainActivity
import com.example.carenest.Medican_Market.Medican_Product
import com.example.carenest.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // This will bind the kotlin file to activity_main
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        // This button will change the activity from main to SignIn
        binding.buttonSignIn.setOnClickListener() {



            // Intent is use to change the activity

            val intent = Intent(this, Sign_In::class.java)
            startActivity(intent)
            binding.textView.setTransitionVisibility(View.VISIBLE)
        }

        // This button will change the activity from main to SignUp
        binding.buttonSignUpp.setOnClickListener() {



            // Intent is use to change the activity
            val intent = Intent(this, Sign_Up::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener(){


            val user = Medican_Product(
                "null",
                "medican",
                34,
                "43",
                "kjkpsdjfioajsdoikfjsiokjfosidjf isjf osijf ioaskjf ksa jfkasj kdfj;lskjf;kasjf ",
                45,
            )
//
            db.collection("Equipments MarketFarmer_Market").document("1").set(user)
            db.collection("Equipments MarketFarmer_Market").document("2").set(user)
            db.collection("Equipments MarketFarmer_Market").document("3").set(user)
            db.collection("Equipments MarketFarmer_Market").document("4").set(user)
            db.collection("Equipments MarketFarmer_Market").document("5").set(user)
            db.collection("Equipments MarketFarmer_Market").document("6").set(user)
            db.collection("Seed MarketFarmer_Market").document("1").set(user)
            db.collection("Seed MarketFarmer_Market").document("2").set(user)
            db.collection("Seed MarketFarmer_Market").document("3").set(user)
            db.collection("Seed MarketFarmer_Market").document("4").set(user)
            db.collection("Seed MarketFarmer_Market").document("5").set(user)
            db.collection("Seed MarketFarmer_Market").document("6").set(user)


            val intent = Intent(this, HealthMainActivity::class.java)
            startActivity(intent)

        }
    }
}

