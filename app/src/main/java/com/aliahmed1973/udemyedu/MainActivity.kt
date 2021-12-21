package com.aliahmed1973.udemyedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aliahmed1973.udemyedu.databinding.ActivityMainBinding
import com.aliahmed1973.udemyedu.network.CourseApi
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController= navHost.navController
        binding.bottomNavigation.setupWithNavController(navController)
        setContentView(binding.root)
    }
}