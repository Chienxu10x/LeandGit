package com.example.appcc.fragment

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSetWallPaperBinding
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

        binding.btnSetWallpaper.setOnClickListener{
            val bitmap = requireContext().getBitmapFromAsset(arl.wallpaper)
            bitmap?.let { it1 -> showSelectScreen(it1) }
        }


    }

    override fun observeData() {
    }

    fun showSelectScreen(bitmap: Bitmap) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        val view = layoutInflater.inflate(R.layout.custom_sheet_wallpaper, null)
        val lock_screen = view.findViewById<TextView>(R.id.lock_screen)
        val home_screen = view.findViewById<TextView>(R.id.home_screen)
        val all_screen = view.findViewById<TextView>(R.id.all_screen)
        val close = view.findViewById<TextView>(R.id.close)

        close.setOnClickListener {
            dialog.dismiss()
        }

//        if (Build.VERSION.SDK_INT <= 24) {
//            lock_screen.gone()
//            home_screen.gone()
//        }

        all_screen.setOnClickListener {
            setWallpaperAll(bitmap)
        }

        lock_screen.setOnClickListener {
            setWallpaperLock(bitmap)
        }

        home_screen.setOnClickListener {
            setWallpaperHome(bitmap)
        }
        dialog.setContentView(view)
        dialog.show()
    }

    fun setWallpaperAll(bitmap: Bitmap) {
        WallpaperManager.getInstance(requireContext()).setBitmap(bitmap)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WallpaperManager.getInstance(requireContext()).setBitmap(
                bitmap, null,
                true, WallpaperManager.FLAG_LOCK
            )
        }
        Toast.makeText(
            requireContext(),
            "Background is set, scroll to right to change app icon",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun setWallpaperLock(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WallpaperManager.getInstance(requireContext())
                .setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
        } else {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val ip: InputStream = ByteArrayInputStream(baos.toByteArray())
            WallpaperManager.getInstance(requireContext())
                .setStream(ip)
        }
        Toast.makeText(
            requireContext(),
            "Background is set lock screen, scroll to right to change app icon",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun setWallpaperHome(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WallpaperManager.getInstance(requireContext())
                .setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
        } else {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val ip: InputStream = ByteArrayInputStream(baos.toByteArray())
            WallpaperManager.getInstance(requireContext())
                .setStream(ip)
        }
        Toast.makeText(
            requireContext(),
            "Background is set home screen, scroll to right to change app icon",
            Toast.LENGTH_SHORT
        ).show()
    }

}