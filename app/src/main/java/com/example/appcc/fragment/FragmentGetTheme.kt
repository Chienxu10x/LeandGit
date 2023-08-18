package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerAndTabs()
        setupClickListeners()

    }

    private fun setupViewPagerAndTabs() {
        val fragmentsList = listOf(
            FragmentIconDetail2(contentX),
            DetailWidgetsFragment(contentX),
            FragmentSetWallPaper(contentX)
        )
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), fragmentsList)
        binding.viewPager.adapter = viewPagerAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Icons"
                1 -> "Widgets"
                2 -> "Theme"
                else -> ""
            }
        }.attach()
    }

    override fun observeData() {}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetThemeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

        binding.btnCoin.setOnClickListener {
            activity?.let { act ->
                (act as MainActivity).replaceFragment(FragmentCoinDetails())
            }
        }
    }

    fun setUpView(): FragmentGetTheme {
        return FragmentGetTheme(contentX)
    }

    override fun baseBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("aaaa", "handleOnBackPressed: aaaa")
                    onBackPress()
                }
            })

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


