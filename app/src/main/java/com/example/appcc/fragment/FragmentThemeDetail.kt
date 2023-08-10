package com.example.appcc.fragment

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.base.BaseFragmentStateAdapter
import com.example.appcc.databinding.FragmentThemeDetailBinding
import com.example.appcc.databinding.FragmentThemesBinding
import com.example.appcc.extension.toAssetPath
import com.google.android.material.tabs.TabLayoutMediator
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class FragmentThemeDetail : BaseFragment() {
    private lateinit var binding : FragmentThemeDetailBinding
    val arg: ThemesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeDetailBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun bindView() {
        val item = arg.contentx
        Log.d("TAGV", "bindView: " + item)

        Glide.with(this).load(Uri.parse(item.previews[0].toAssetPath())).into(binding.imageThemeItem)
    }

    override fun observeData() {
    }


}