package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.RecyclerAdapterIcon
import com.example.appcc.adapter.RecyclerAdapterTheme
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentIconsBinding
import com.example.appcc.extension.navigateTo
import com.example.appcc.model.ContentX
import com.example.appcc.viewmodel.IconsViewModel
import com.google.android.material.tabs.TabLayout


class IconsFragment :BaseFragment() {
    private lateinit var binding : FragmentIconsBinding
    private val iconViewModel : IconsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIconsBinding.inflate(layoutInflater)
//        (activity as MainActivity).setTitleApp("Icons")
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
    private val recyclerAdapterIcon = RecyclerAdapterIcon{
//        toDetail(it)
        activity?.let {act->
            var fragmentIconDetail: FragmentIconDetail = FragmentIconDetail(it).setUpView()
            (act as MainActivity).replaceFragment(fragmentIconDetail)
        }
    }

    override fun observeData() {
        iconViewModel.currentTheme.observe(this){
            recyclerAdapterIcon.submitList(it.content)

            binding.recyclerviewIcon.adapter = recyclerAdapterIcon
        }
        iconViewModel.allTheme.observe(this){
            binding.tabMenu.removeAllTabs()
            it.contents.forEach{
                val tab = binding.tabMenu.newTab()
                tab.text = it.title
                binding.tabMenu.addTab(tab)
            }
        }
        iconViewModel.loadAllResource(requireContext())

    }

//    private fun toDetail(contentX: ContentX){
//            val action : NavDirections = IconsFragmentDirections.actionIconsFragmentToFragmentAppLauncher2(contentX)
//            navigateTo(action)
//
//    }





}