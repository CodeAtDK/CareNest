package com.example.carenest.HealthMain

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.carenest.HomeFragment
import com.example.carenest.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HealthMainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    // initialize to drawerlayout
    private lateinit var drawerLayout: DrawerLayout
    // initialize to authentication
    private lateinit var auth: FirebaseAuth

    // this will close the app
//    override fun onAppExit(){
//        finishAffinity()
//        exitProcess(0)
//    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_main)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)


        // ...
        // Initialize Firebase Auth
        auth = Firebase.auth


        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        if(savedInstanceState == null){
            //replaceFragment(Home())
            navigationView.setCheckedItem(R.id.nav_home)
        }

       replaceFragment(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

              R.id.nav_home -> replaceFragment(HomeFragment())
//            R.id.nav_ChatBot -> replaceFragment(Chat_Bot())
//            R.id.nav_Conact_Us -> replaceFragment(Contact_Page())
//            R.id.nav_Log_Out -> finish()

        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    //    override fun finish(){
//
//        Firebase.auth.signOut()
//
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//
//    }



    fun replaceFragment(fragment: Fragment){

        val transition: FragmentTransaction = supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragment_container,fragment)
        transition.addToBackStack("Tag").commit()

    }


    override fun onBackPressed() {
        super.onBackPressed()
//        super.onBackPressed()


        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            onBackPressedDispatcher.onBackPressed()
        }
        replaceFragment(HomeFragment())
//        System.exit(-1)

     //   val frag = supportFragmentManager.findFragmentByTag(Home::class.java.simpleName)
        // Toast.makeText(this,frag.toString(),Toast.LENGTH_SHORT).show()
        // Log.d("TAG",frag.toString())




    }
}