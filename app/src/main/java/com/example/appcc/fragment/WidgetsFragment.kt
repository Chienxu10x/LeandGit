package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.appcc.base.BaseFragment
import com.example.appcc.base.BaseFragmentStateAdapter
import com.example.appcc.databinding.FragmentWidgetsBinding
import com.example.appcc.extension.gone
import com.example.appcc.extension.visibble
import com.example.appcc.model.WidgetXX
import com.example.appcc.viewmodel.WidgetViewModel
import com.google.android.material.tabs.TabLayout

class WidgetsFragment : BaseFragment() {
    private lateinit var binding: FragmentWidgetsBinding
    private val widgetViewModel: WidgetViewModel by activityViewModels()
    val widgetFragment1 = WidgetsFragment1()
    private var currentWidget: WidgetXX? = null

    override fun bindView() {
        widgetViewModel.loadAllResource(requireContext())
        val adapter = BaseFragmentStateAdapter(this)
        adapter.addFragment(widgetFragment1, "")
        binding.vpWidget.adapter = adapter
        binding.tabMenu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                widgetViewModel.allWidget.value?.let {
                    binding.loadingView.visibble()
                    currentWidget = it.widgets[tab.position].widget[0]

                    widgetFragment1.icon1 = it.widgets[tab.position].widget[0].calendar
                    widgetFragment1.icon2 = it.widgets[tab.position].widget[0].widget[0]
                    widgetFragment1.icon3 = it.widgets[tab.position].widget[0].widget[1]
                    widgetFragment1.icon4 = it.widgets[tab.position].widget[0].calendar

                    widgetFragment1.font1 = it.widgets[tab.position].widget[0].text_font
                    widgetFragment1.font2 = it.widgets[tab.position].widget[0].number_font

                    widgetFragment1.fontColor1 = it.widgets[tab.position].widget[0].text_color_1
                    widgetFragment1.fontColor2 = it.widgets[tab.position].widget[0].text_color_2
                    widgetFragment1.fontColor3 = it.widgets[tab.position].widget[0].number_color_1
                    widgetFragment1.fontColor4 = it.widgets[tab.position].widget[0].number_color_2


                    widgetFragment1.icon5 = it.widgets[tab.position].widget[0].long_widget[0]
                    widgetFragment1.icon6 = it.widgets[tab.position].widget[0].long_widget[1]
                    widgetFragment1.icon7 = it.widgets[tab.position].widget[0].big_widget
                    binding.vpWidget.post {
                        widgetFragment1.reloadView()
                        widgetViewModel.setCurrentWidgetPosion(binding.tabMenu.selectedTabPosition + 1)
//                        Log.d("TAG", "onTabSelected: " + (binding.tabMenu.selectedTabPosition + 1))
                        binding.vpWidget.setCurrentItem(0, false)
                        android.os.Handler().postDelayed({
                            binding.loadingView.gone()
                        }, 0)
                    }


                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

//        tv_set_widget.setOnClickListener {
//            widgetViewModel.setCurrentWidget(tabMenu.selectedTabPosition + 1)
//            currentWidget?.let {
//                widgetViewModel.setTextColor1(it.text_color_1)
//                widgetViewModel.setTextColor2(it.text_color_2)
//                widgetViewModel.setTextColor3(it.number_color_1)
//                widgetViewModel.setTextColor4(it.number_color_2)
//            }
//            (requireParentFragment().requireParentFragment() as MainFragment).findNavController()
//                .navigate(R.id.action_main_to_success_widget)
//        }

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentWidgetsBinding.inflate(layoutInflater)
        return binding.root
    }


}