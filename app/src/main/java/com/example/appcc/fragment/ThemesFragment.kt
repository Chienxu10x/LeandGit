package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.ViewPagerAdapterTheme

class ThemesFragment : Fragment() {

    private lateinit var viewPagerAdapterTheme: ViewPagerAdapterTheme
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_themes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapterTheme = ViewPagerAdapterTheme(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = viewPagerAdapterTheme


    }
}