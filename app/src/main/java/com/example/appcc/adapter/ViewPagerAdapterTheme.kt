package com.example.appcc.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appcc.fragment.FragmentThemeChild2
import com.example.appcc.fragment.FragmetThemeChild

class ViewPagerAdapterTheme(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var tabNameChangedListener: OnTabNameChangedListener? = null

    fun setOnTabNameChangedListener(listener: OnTabNameChangedListener) {
        tabNameChangedListener = listener
    }


    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {

       return when(position){
           0->FragmetThemeChild();
           1->FragmentThemeChild2();
           else->FragmetThemeChild();
       }
    }

}

interface OnTabNameChangedListener {
    fun onTabNameChanged(position: Int, newName: String)
}


private const val ARG_OBJECT = "object"


