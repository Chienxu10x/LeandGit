package com.example.appcc.fragment

import android.app.ActionBar.LayoutParams
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSetWallPaperBinding
import com.example.appcc.dialog.SetWallpaperDialog
import com.example.appcc.extension.getBitmapFromAsset
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class FragmentSetWallPaper(contentX: ContentX) : BaseFragment() {
    private lateinit var binding: FragmentSetWallPaperBinding
    val arl = contentX

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetWallPaperBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindView() {
        Glide.with(this).load(Uri.parse(arl.wallpaper.toAssetPath())).into(binding.imgWallPaper)

        binding.btnSetWallpaper.setOnClickListener {
            val bitmap = requireContext().getBitmapFromAsset(arl.wallpaper)
            bitmap?.let {
                val dialogWallpaper = SetWallpaperDialog(bitmap)
                dialogWallpaper.isCancelable = false
                dialogWallpaper.show(childFragmentManager, DIALOG_WALLPAPER)
            }


        }


    }

    override fun observeData() {
    }

    companion object {
        private const val DIALOG_WALLPAPER = "DIALOG_WALLPAPER"
    }


}