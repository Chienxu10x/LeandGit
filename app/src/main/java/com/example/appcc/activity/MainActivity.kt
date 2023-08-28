package com.example.appcc.activity


import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration

import androidx.navigation.ui.setupWithNavController
import com.example.appcc.R
import com.example.appcc.base.BaseActivity
import com.example.appcc.databinding.ActivityMainBinding
import com.example.appcc.fragment.FragmentCoinDetails
import com.example.appcc.fragment.FragmentSeachTheme
import com.example.appcc.fragment.FragmentType
import com.example.appcc.fragment.IconsFragment
import com.example.appcc.fragment.PrivacyPolicyFragment
import com.example.appcc.fragment.SettingFragment

import dagger.hilt.android.AndroidEntryPoint

@Suppress("DUPLICATE_LABEL_IN_WHEN")
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navBottom.setupWithNavController(findNavController(R.id.nav_container))
        setSupportActionBar(binding.myToolbar)
        binding.themes.text = getString(R.string.themes)
        binding.menuSetting.setOnClickListener {
            replaceFragment(SettingFragment())
        }

        binding.btnReplaceCoin.setOnClickListener{
            replaceFragment(FragmentCoinDetails())
        }

        binding.btnSeachTheme.setOnClickListener{
            replaceFragment(FragmentSeachTheme())
        }

        binding.menuType.setOnClickListener{
            replaceFragment(FragmentType())
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

    fun setTitleApp() {
        binding.themes.text = "bbbbbb"
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack()
        if (binding.myToolbar.visibility == View.GONE) {
            binding.myToolbar.visibility = View.VISIBLE
        }
        if (binding.navBottom.visibility == View.GONE) {
            binding.navBottom.visibility = View.VISIBLE
        }
    }
}









