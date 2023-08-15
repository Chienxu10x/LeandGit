package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.ViewPagerAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentGetThemeBinding
import com.example.appcc.model.ContentX
import com.example.appcc.model.WidgetXX
import com.google.android.material.tabs.TabLayoutMediator

class FragmentGetTheme(contentX: ContentX) : BaseFragment() {
    private lateinit var binding : FragmentGetThemeBinding
    val contentX = contentX





    override fun bindView() {
//        Log.d("TAGTT", "bindView: " + item)
        val fragmentsList = listOf(FragmentIconDetail2(contentX), DetailWidgetsFragment(contentX), FragmentSetWallPaper(contentX)) // Replace with your fragment instances
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), fragmentsList)
        binding.viewPager.adapter = viewPagerAdapter

        val tabLayout = binding.tabLayout  // Get a reference to the TabLayout
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            // Configure the tabs here
            // You can set tab text or custom views based on your requirements
            tab.text = when (position) {
                0 -> "Icons"
                1 -> "Widgets"
                2 -> "Theme"
                else -> ""
            }
        }.attach()

        binding.btnBack.setOnClickListener{
            onBackPress()
        }
    }

    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetThemeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onStop() {
        super.onStop()

    }
    fun setUpView(): FragmentGetTheme {
        return FragmentGetTheme(contentX)
    }

    private fun onBackPress() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (act as MainActivity).removeFragment(this)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

}


