package com.example.appcc.base

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appcc.R
import androidx.databinding.BindingAdapter
import com.example.appcc.databinding.ActivityMainBinding
import com.example.appcc.extension.visibble
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        super.onCreate(savedInstanceState)
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

    fun replaceFragemtSetting(fragment: Fragment) {
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
}


//    abstract fun onBottomViewProvides(): BottomNavigationView?

//    fun hideBottomView() {
//        onBottomViewProvides()?.visibble()
//    }
//
//    fun showBottomView() {
//        onBottomViewProvides()?.visibble()
//    }



