package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.adapter.ViewPagerAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentGetThemeBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentGetTheme : BaseFragment() {
    private lateinit var binding : FragmentGetThemeBinding
    val arg: FragmentGetThemeArgs by navArgs()

        override fun bindView() {
            val item = arg.contentx
            Log.d("TAGTT", "bindView: " + item)
            val fragmentsList = listOf(FragmentIconDetail2(item), WidgetsFragment(), FragmentSetWallPaper(item)) // Replace with your fragment instances
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

}