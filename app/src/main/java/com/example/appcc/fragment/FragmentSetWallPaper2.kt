package com.example.appcc.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSetWallPaper2Binding
import com.example.appcc.databinding.FragmentSetWallPaperBinding
import com.example.appcc.dialog.SetWallpaperDialog
import com.example.appcc.extension.getBitmapFromAsset
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX

class FragmentSetWallPaper2(contentX: ContentX) : BaseFragment() {
    private lateinit var binding: FragmentSetWallPaper2Binding
    val arl = contentX

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetWallPaper2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindView() {
        binding.btnBack.setOnClickListener{
            onBackPressed()

        }
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
    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)
        }
    }

    companion object {
        private const val DIALOG_WALLPAPER = "DIALOG_WALLPAPER"
    }


}