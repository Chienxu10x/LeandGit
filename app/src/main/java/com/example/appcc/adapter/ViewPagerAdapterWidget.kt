package com.example.appcc.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appcc.fragment.FragmentWidgetChild

class ViewPagerAdapterWidget(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> FragmentWidgetChild()
            1-> FragmentWidgetChild()
            else-> FragmentWidgetChild()
        }
    }
}