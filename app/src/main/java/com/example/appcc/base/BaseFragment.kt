package com.example.appcc.base

import android.os.Bundle
import android.util.Log

import android.view.View

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.extension.gone
import com.example.appcc.extension.visibble
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onStart() {
        super.onStart()
//        Log.d("TAG", "onStart: "+"errr")
        bindView()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (requireActivity() as BaseActivity).showBottomView()
        bindView()
        observeData()
        baseBackPressed()
    }
//

    //
    //LogInstance.e("___________Fragment " + this@BaseFragment::class)
    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun bindView()
    abstract fun observeData()
    open fun hideInBaseNavigationView() {
        (activity as? MainActivity?)?.let {
            it.hideNavigationView()
        }
    }
    open fun hideInBaseToolBar(){
        (activity as? MainActivity)?.let {
            it.hideToolBar()
        }
    }
    fun showInBaseNavigationView() {
        (activity as? MainActivity?)?.let {
            it.showNavigationView()
        }
    }
    fun showIntoolBar() {
        (activity as? MainActivity?)?.let {
            it.showToolBar()
        }
    }
    open fun baseBackPressed() {}
    fun titleToolBar(){
                (activity as MainActivity).setTitleApp()
    }


}


//


//    protected fun hideBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.gone()
//    }
//
//    protected fun showBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.visibble()    }


