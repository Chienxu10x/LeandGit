package com.example.appcc.activity



import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration

import androidx.navigation.ui.setupWithNavController
import com.example.appcc.R
import com.example.appcc.base.BaseActivity
import com.example.appcc.databinding.ActivityMainBinding
import com.example.appcc.fragment.FragmentCoinDetails
import com.example.appcc.fragment.FragmentSeachTheme
import com.example.appcc.fragment.IconsFragment
import com.example.appcc.fragment.PrivacyPolicyFragment
import com.example.appcc.fragment.SettingFragment

import dagger.hilt.android.AndroidEntryPoint

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navBottom.setupWithNavController(findNavController(R.id.nav_container))
        setSupportActionBar(binding.myToolbar)

        binding.menuSetting.setOnClickListener {
//           val intent =Intent(this@MainActivity,SettingActivity::class.java)
//           startActivity(intent)
            replaceFragment(SettingFragment())
        }

        binding.btnReplaceCoin.setOnClickListener{
            replaceFragment(FragmentCoinDetails())
        }

        binding.btnSeachTheme.setOnClickListener{
            replaceFragment(FragmentSeachTheme())
        }
















    }

    override fun observeData() {
    }
    fun hideNavigationView() {
        binding.navBottom.visibility = View.GONE
    }

    fun showNavigationView() {
        binding.navBottom.visibility = View.VISIBLE
    }
    fun hideToolBar() {
        binding.myToolbar.visibility = View.GONE
    }

    fun showToolBar() {
        binding.myToolbar.visibility = View.VISIBLE
    }
}
//    override fun onBottomViewProvides(): BottomNavigationView? {
//        return null
//    }

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








