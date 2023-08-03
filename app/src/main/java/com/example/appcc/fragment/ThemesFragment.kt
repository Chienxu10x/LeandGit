package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.ViewPagerAdapterTheme
import com.example.appcc.base.BaseFragment
import com.example.kittheme.extension.navigateTo
import com.example.kittheme.model.ContentX
import androidx.navigation.fragment.findNavController


class ThemesFragment : BaseFragment(R.layout.fragment_themes) {

    private lateinit var viewPagerAdapterTheme: ViewPagerAdapterTheme
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_themes, container, false)

    }

    override fun bindView() {

    }

    override fun observeData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapterTheme = ViewPagerAdapterTheme(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = viewPagerAdapterTheme


    }


//    fun toDetail(contentX: ContentX){
//
//        val action : NavDirections = FragmetThemeChildDirections.actionFragmetThemeChildToFragmentThemeDetail(contentX)
//        navigateTo(action)
//    }
//
//    fun toInfor(type: Long){
//        val action : NavDirections = FragmetThemeChildDirections.actionMainToInfor(type)
//        navigateTo(action)
//
//    }
}