package com.example.appcc.fragment

import android.os.Bundle

import android.view.Gravity
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSettingBinding


class SettingFragment : BaseFragment(R.layout.fragment_setting) {


    private lateinit var drawerLayout : DrawerLayout

    private lateinit var binding: FragmentSettingBinding
    override fun bindView() {
        binding.apply {
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

            }
            lnReview.setOnClickListener {

            }
            lnLanguage.setOnClickListener{

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
        val rootView = inflater.inflate(R.layout.fragment_setting, container, false)

        // Access the DrawerLayout from the parent activity


        return rootView
    }


    companion object {

    }
}