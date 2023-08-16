package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSetWallPaperBinding
import com.example.appcc.databinding.FragmentWallpaperBinding

class WallpaperFragment : BaseFragment() {

    private lateinit var binding: FragmentWallpaperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWallpaperBinding.inflate(layoutInflater)
        return binding.root

    }
    override fun bindView() {

    }

    override fun observeData() {

    }

}