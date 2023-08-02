package com.example.appcc.base

import android.os.Bundle
<<<<<<< HEAD
=======
import android.view.View
>>>>>>> 921213202be9a9f5d9e2f477c909c2e79c98e51a
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.appcc.extension.gone
import com.example.appcc.extension.visibble

<<<<<<< HEAD

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        LogInstance.e("___________Fragment " + this@BaseFragment::class)
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
//    protected fun showLoading() {
//    }
//
//    protected fun dismissLoading() {
//    }
//
//    abstract fun bindView()
//    abstract fun observeData()
//
=======
abstract class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //LogInstance.e("___________Fragment " + this@BaseFragment::class)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun showLoading() {
    }

    protected fun dismissLoading() {
    }

    abstract fun bindView()
    abstract fun observeData()

>>>>>>> 921213202be9a9f5d9e2f477c909c2e79c98e51a
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        (requireActivity() as BaseActivity).showBottomView()
//        bindView()
//        observeData()
//    }
//
//    protected fun hideBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.gone()
//    }
//
//    protected fun showBottomView() {
//        (requireActivity() as BaseActivity).onBottomViewProvides()?.visibble()    }

}