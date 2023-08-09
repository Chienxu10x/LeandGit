package com.example.appcc.base

import android.os.Bundle

import android.view.View

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.appcc.R
import com.example.appcc.extension.gone
import com.example.appcc.extension.visibble
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    abstract fun bindView()
    abstract fun observeData()


}


//


//    protected fun hideBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.gone()
//    }
//
//    protected fun showBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.visibble()    }


