package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.ViewPagerAdapterTimeline
import com.example.appcc.databinding.ActivityMainBinding
import com.example.appcc.databinding.FragmentIconsBinding
import com.example.appcc.databinding.FragmentTimelineBinding
import com.google.android.material.tabs.TabLayoutMediator

class TimelineFragment : Fragment() {
    private lateinit var binding: FragmentTimelineBinding
    private lateinit var viewPagerAdapterTimeline: ViewPagerAdapterTimeline
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimelineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapterTimeline = ViewPagerAdapterTimeline(this);
        binding.viewPageTimeline.adapter = viewPagerAdapterTimeline
        TabLayoutMediator(binding.tableLayoutTimeline, binding.viewPageTimeline) { tab, position ->
            tab.text = if (position == 0) resources.getString(R.string.theme)
            else resources.getString(R.string.icons)
        }.attach()


    }
}