package com.example.taskmanager

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.e("thjj","msg"+it)
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.navigate(R.id.authFragment)

        if (!pref.isOnBoardingShow())
            navController.navigate(R.id.onBoardingFragment)

        if (FirebaseAuth.getInstance().currentUser?.uid == null) {
           // navController.navigate(R.id.phoneFragment)
        }



        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val fragmentWithoutBottomNav = setOf(
            R.id.onBoardingFragment,
            R.id.phoneFragment,
            R.id.verifyFragment,
            R.id.authFragment

        )
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            if (fragmentWithoutBottomNav.contains(destination.id)){
                    navView.isVisible = false
                    supportActionBar?.hide()
                }else {
                navView.isVisible = true
                supportActionBar?.show()

                }

        }
    }
}