package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentDetailWidgetsFargmentBinding
import com.example.appcc.databinding.FragmentWidgetsBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.viewmodel.WidgetViewModel

class DetailWidgetsFargment : BaseFragment() {
    private lateinit var binding: FragmentDetailWidgetsFargmentBinding
    private val widgetViewModel: WidgetViewModel by activityViewModels()
    override fun bindView() {
        binding.imageBack.setOnClickListener {
            onBackPress()
        }
//        Log.d("TAG", "bindView: "+widgetViewModel.getCurrentWidgetPosion())
//        Log.d("TAG", "bindView: "+widgetViewModel.getStringCurrentWidge())

        Glide.with(this).load(widgetViewModel.getStringCurrentWidge()?.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget)
        binding.btnSetWid.setOnClickListener{
            widgetViewModel.setCurrentWidget(widgetViewModel.getCurrentWidgetPosion())
            Toast.makeText(requireContext(),"OK", Toast.LENGTH_SHORT).show();

        }
    }

    override fun observeData() {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailWidgetsFargmentBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun setUpView(): DetailWidgetsFargment {
        return DetailWidgetsFargment()
    }

    companion object {

    }

    private fun onBackPress() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (act as MainActivity).removeFragment(this)
        }
    }
}