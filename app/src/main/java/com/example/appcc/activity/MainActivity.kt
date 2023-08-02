package com.example.appcc.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appcc.R
import com.example.appcc.base.BaseActivity
import com.example.appcc.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navBottom.setupWithNavController(findNavController(R.id.nav_container))
        setSupportActionBar(binding.myToolbar)
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_menu -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

//    override fun bindView() {
//        TODO("Not yet implemented")
//    }
//
//    override fun observeData() {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBottomViewProvides(): BottomNavigationView? {
//        TODO("Not yet implemented")
//    }

}