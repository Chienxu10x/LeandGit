package com.example.appcc.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appcc.fragment.FragmentTimelineChild
import com.example.appcc.fragment.IconchildFragment

class ViewPagerAdapterTimeline(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> FragmentTimelineChild()
            else -> IconchildFragment()

        }
    }
}