package com.example.appcc.activity

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appcc.R
import com.example.appcc.base.BaseActivity
import com.example.appcc.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : BaseActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navBottom.setupWithNavController(findNavController(R.id.nav_container))
        setSupportActionBar(binding.myToolbar)

        binding.apply {
            toggle=ActionBarDrawerToggle(
                this@MainActivity, drawerLayout,R.string.open,R.string.close
            )
        }

//        drawerLayout = findViewById(R.id.drawerLayout)
//        binding.navView.


//        binding.menuSetting.setOnClickListener {
//            // Show the drawer when the button is clicked
////            drawerLayout.openDrawer(GravityCompat.START)
//        }



    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_menu -> {
            // User chose the "Settings" item, show the app settings UI...
            drawerLayout.openDrawer(GravityCompat.START)
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