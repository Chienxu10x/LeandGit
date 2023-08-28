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
import com.example.appcc.databinding.FragmentCoinDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCoinDetails : BaseFragment() {
    private lateinit var binding: FragmentCoinDetailsBinding
    override fun bindView() {
        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }
    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
}