package com.example.appcc.fragment

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.base.BaseFragmentStateAdapter
import com.example.appcc.databinding.FragmentThemeDetailBinding
import com.example.appcc.databinding.FragmentThemesBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class FragmentThemeDetail : BaseFragment() {
    private lateinit var binding : FragmentThemeDetailBinding
//    val arg: FragmentThemeDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeDetailBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun bindView() {
//        val item = arg.contentx
//        val adapter = BaseFragmentStateAdapter(this)
//        //put icon app
//        val fragment1 = FullImageFragment()
//        val bundle1 = Bundle()
//        bundle1.putString("image", item.previews[0])
//        fragment1.arguments = bundle1
//        adapter.addFragment(fragment1, "")
//
//        val fragment2 = FullImageFragment()
//        val bundle2 = Bundle()
//        bundle2.putString("image", item.previews[1])
//        fragment2.arguments = bundle2
//        adapter.addFragment(fragment2, "")
//
//        val fragment3 = FullImageFragment()
//        val bundle3 = Bundle()
//        bundle3.putString("image", item.previews[2])
//        fragment3.arguments = bundle3
//        adapter.addFragment(fragment3, "")
//
//        //fragment set wallpaper
//        val wallPaperFragment = FullImageFragment()
//        val bundle = Bundle()
//        bundle.putString("image", item.wallpaper)
//        bundle.putBoolean("wallpaper", true)
//        wallPaperFragment.arguments = bundle
//        adapter.addFragment(wallPaperFragment, "")
//
//        binding.vpTheme.adapter = adapter
//
//        TabLayoutMediator(binding.tabTheme, binding.vpTheme) { tab, position ->
//            tab.text = adapter.getTitle(position)
//            binding.vpTheme.setCurrentItem(tab.position, true)
//        }.attach()
    }

    override fun observeData() {
    }


}