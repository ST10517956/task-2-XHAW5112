package com.pawsitive.petacademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pawsitive.petacademy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Bottom bar (Home, Overview, Fees, Contact) mirrors the app wireframes.
        binding.bottomNav.setupWithNavController(navController)

        // Toolbar's logo icon doubles as the "About Us" entry point (hamburger in the wireframes).
        binding.toolbar.setNavigationOnClickListener {
            navController.navigate(R.id.aboutFragment)
        }

        // Highlight the correct bottom-nav tab; hide it while on screens that aren't tabs
        // (course detail, about us) so it behaves like the wireframes' sub-pages.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.menu.findItem(destination.id)?.let {
                binding.bottomNav.selectedItemId = destination.id
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
