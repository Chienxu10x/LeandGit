package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentPrivacyPolicy2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPrivacyPolicy : BaseFragment() {
    private lateinit var binding: FragmentPrivacyPolicy2Binding
    override fun bindView() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun observeData() {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivacyPolicy2Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun onSetupView(): FragmentPrivacyPolicy {
        return FragmentPrivacyPolicy()
    }

    companion object {
        fun onSetupView(): FragmentPrivacyPolicy {
            return FragmentPrivacyPolicy()
        }
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
}