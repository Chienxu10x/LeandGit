package com.example.appcc.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.appcc.BuildConfig
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSettingBinding
import com.example.appcc.utils.shareApp

class SettingFragment : BaseFragment(R.layout.fragment_setting) {
    private lateinit var binding: FragmentSettingBinding
    override fun bindView() {
        binding.apply {
            imageBack.setOnClickListener {
                onBackPressed()
            }
            lnLoginSignup.setOnClickListener {

            }
            lnEarnReward.setOnClickListener {

            }
            lnFavourite.setOnClickListener {

            }
            lnMyTimelines.setOnClickListener {

            }
            lnPolicy.setOnClickListener {

            }
            lnTermOfUse.setOnClickListener {

            }
            lnContact.setOnClickListener {

            }
            lnShare.setOnClickListener {
                requireContext().shareApp()
            }
            lnReview.setOnClickListener {

            }
            lnLanguage.setOnClickListener {

            }
            lnFAQ.setOnClickListener {

            }
        }
    }

    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }

    companion object {

    }
}