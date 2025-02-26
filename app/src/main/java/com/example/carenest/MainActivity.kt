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
import com.example.carenest.Health_Education.ViewModel.DataClass_Health_Education
import com.example.carenest.Medican_Market.Medican_Product
import com.example.carenest.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.net.URL

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


//            val user = Medican_Product(
//                "null",
//                "medican",
//                34,
//                "43",
//                "kjkpsdjfioajsdoikfjsiokjfosidjf isjf osijf ioaskjf ksa jfkasj kdfj;lskjf;kasjf ",
//                45,
//            )
//
//            db.collection("Equipments MarketFarmer_Market").document("1").set(user)
//            db.collection("Equipments MarketFarmer_Market").document("2").set(user)
//            db.collection("Equipments MarketFarmer_Market").document("3").set(user)
//            db.collection("Equipments MarketFarmer_Market").document("4").set(user)
//            db.collection("Equipments MarketFarmer_Market").document("5").set(user)
//            db.collection("Equipments MarketFarmer_Market").document("6").set(user)
//            db.collection("Seed MarketFarmer_Market").document("1").set(user)
//            db.collection("Seed MarketFarmer_Market").document("2").set(user)
//            db.collection("Seed MarketFarmer_Market").document("3").set(user)
//            db.collection("Seed MarketFarmer_Market").document("4").set(user)
//            db.collection("Seed MarketFarmer_Market").document("5").set(user)
//            db.collection("Seed MarketFarmer_Market").document("6").set(user)

//
            val user = Medican_Product(
                "MarketPlace/Screenshot 2025-02-26 204015.png",
                "Thermometer CQR-T800",
                1399,
                "Vandelay Infrared Thermometer CQR-T800 - Made In India, Non Contact IR Thermometer, Forehead Temperature Gun",
                "Non Contact and Accurate Readings : Non-contact infrared measurement. Measuring distance is under 1-2inch, while avoiding cross infection. The forehead thermometer medical has been clinically tested, and has a very small error margin. It is actually more accurate and healthier than standard mercury thermometers.\n" +
                        "Sound Alarm : High-accuracy infrared measurement, when the temperature is over, audible alert will trigger. The HD LED screen displays clear and crisp numbers that are easy to read.\n" +
                        "Multi-Purposes : The Forehead thermometer is designed for all ages: adults, infants and elders. It support to take human temperature.\n" +
                        "Memory Feature : It can store up to 32 temperature readings and recall them at any time. This comes in handy for keeping a record, and makes it convenient for babies, children, adults. Also it has auto shutdown feature for power saving.\n" +
                        "For warranty or service issues kindly contact brand support",
                1

            )
            db.collection("Equipments MarketFarmer_Market").document("1").set(user)
            db.collection("Equipments MarketFarmer_Market").document("2").set(user)
            db.collection("Equipments MarketFarmer_Market").document("3").set(user)
            db.collection("Equipments MarketFarmer_Market").document("4").set(user)
            db.collection("Equipments MarketFarmer_Market").document("5").set(user)



            val intent = Intent(this, HealthMainActivity::class.java)
            startActivity(intent)

        }
    }
    private fun getYouTubeVideoId(url: String): String? {
        return try {
            val uri = URL(url).toURI()
            val query = uri.query
            query.split("&").find { it.startsWith("v=") }?.split("=")?.get(1)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

