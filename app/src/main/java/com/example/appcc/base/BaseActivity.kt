package com.example.appcc.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appcc.R
import androidx.databinding.BindingAdapter
import com.example.appcc.data.DataSave
import com.example.appcc.databinding.ActivityMainBinding
import com.example.appcc.extension.visibble
import com.example.appcc.viewmodel.IconViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    private val iconViewModel: IconViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguages()
        bindView()
        observeData()
//        LogInstance.e("___________Activity " + this@BaseActivity::class)
    }

    //        LogInstance.e("___________Activity " + this@BaseActivity::class)
    abstract fun bindView()
    abstract fun observeData()

    fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.drawerLayout,
            fragment
        ) // R.id.fragment_container là ID của một ViewGroup trong layout của Activity
        fragmentTransaction.commit()
    }

    fun removeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }
    private fun setLanguages() {
        val config = resources.configuration
        val displayLanguage = DataSave.language ?: "en"
        val locale = Locale(displayLanguage)
        Locale.setDefault(locale)
        config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        iconViewModel.setLanguage(displayLanguage)
    }
}


//    abstract fun onBottomViewProvides(): BottomNavigationView?

//    fun hideBottomView() {
//        onBottomViewProvides()?.visibble()
//    }
//
//    fun showBottomView() {
//        onBottomViewProvides()?.visibble()
//    }



