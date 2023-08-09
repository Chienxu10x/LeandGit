package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.RecyclerAdapterTheme

import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentThemesBinding
import com.example.appcc.model.ContentX
import com.example.appcc.extension.navigateTo
import com.example.appcc.viewmodel.IconViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

class ThemesFragment : BaseFragment() {
private lateinit var binding: FragmentThemesBinding
    private val iconViewModel : IconViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindView() {
    binding.tabMenu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab) {
                iconViewModel.getThemeByFilter(tab.position)
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

    })


    }

    private val recyclerAdapterTheme = RecyclerAdapterTheme{
        (requireParentFragment().requireParentFragment() as ThemesFragment).toDetail(it)
    }

    override fun observeData() {
        iconViewModel.currentTheme.observe(this){
            Log.d("TAG", "observeData: "+"a0")
            recyclerAdapterTheme.submitList(it.content)
            Log.d("TAG", "observeData: "+it.content.get(0).previews)
            binding.recyclerviewTheme.adapter = recyclerAdapterTheme
        }

        iconViewModel.allTheme.observe(requireActivity()){
            binding.tabMenu.removeAllTabs()
            it.contents.forEach{
                val tab = binding.tabMenu.newTab()
                tab.text = it.title
//                Log.d("TAG", "observeData: "+it.content.get(0).previews)
                binding.tabMenu.addTab(tab)
            }
        }
        iconViewModel.loadAllResource()

    }

    fun toDetail(contentX: ContentX){
//        val action : NavDirections = FragmetThemeChildDirections.actionFragmetThemeChildToFragmentThemeDetail()
//        navigateTo(action)
    }

    fun toInfor(type: Long){
        val action : NavDirections = ThemesFragmentDirections.actionThemesFragmentToTimelineFragment()
        navigateTo(action)

    }
}