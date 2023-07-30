package com.example.appcc.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.example.appcc.databinding.ActivityMainBinding
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
//        bindView()
//        observeData()
//        LogInstance.e("___________Activity " + this@BaseActivity::class)
    }

//    abstract fun bindView()
//    abstract fun observeData()
//    abstract fun onBottomViewProvides(): BottomNavigationView?
//
//    fun hideBottomView() {
//        onBottomViewProvides()?.visibble()
//    }
//
//    fun showBottomView() {
//        onBottomViewProvides()?.visibble()
//    }
//
//    fun hideKeyboard() {
//        val imm: InputMethodManager =
//            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        var view: View? = currentFocus
//        if (view == null) {
//            view = View(this)
//        }
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
//    }
}