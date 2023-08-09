package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.appcc.R
import com.example.appcc.adapter.ViewPagerAdapterWidget
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentThemesBinding
import com.example.appcc.databinding.FragmentWidgetsBinding
import com.example.appcc.viewmodel.WidgetViewModel

class WidgetsFragment : BaseFragment() {
    private lateinit var binding : FragmentWidgetsBinding
    private val widgetViewModel: WidgetViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWidgetsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindView() {
        widgetViewModel.loadAllResource(requireContext())


    }

    override fun observeData() {
        widgetViewModel.allWidget.observe(this) {
            binding.tabMenu.removeAllTabs()
            it.widgets.forEach {
                val tab = binding.tabMenu.newTab()
                tab.text = it.title
                binding.tabMenu.addTab(tab)
            }
        }
    }


}