package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.ViewPagerAdapterTheme
import com.example.appcc.adapter.ViewPagerAdapterTimeline

class TimelineFragment : Fragment() {

    private lateinit var viewPagerAdapterTimeline: ViewPagerAdapterTimeline
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapterTimeline = ViewPagerAdapterTimeline(this);
        viewPager = view.findViewById(R.id.viewPageTimeline)
        viewPager.adapter = viewPagerAdapterTimeline
    }
}