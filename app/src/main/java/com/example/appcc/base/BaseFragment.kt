package com.example.appcc.base

import android.os.Bundle

import android.view.View

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.appcc.extension.gone
import com.example.appcc.extension.visibble
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes layout: Int) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        LogInstance.e("___________Fragment " + this@BaseFragment::class)

//        LogInstance.e("___________Fragment " + this@BaseFragment::class)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (requireActivity() as BaseActivity).showBottomView()
        bindView()
        observeData()
    }
//

    //
    //LogInstance.e("___________Fragment " + this@BaseFragment::class)
    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun showLoading() {
    }

    protected fun dismissLoading() {
    }

    abstract fun bindView()
    abstract fun observeData()

}


