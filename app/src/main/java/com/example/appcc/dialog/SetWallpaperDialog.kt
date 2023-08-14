package com.example.appcc.dialog

import android.app.ActionBar
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.appcc.R
import com.example.appcc.databinding.FragmentSetWallpaperDialogBinding
import com.example.appcc.extension.gone
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class SetWallpaperDialog(private val bitmap: Bitmap ) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSetWallpaperDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSetWallpaperDialogBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        isCancelable=true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSelectScreen(bitmap)
    }
    fun showSelectScreen(bitmap: Bitmap) {

        binding.close.setOnClickListener {
            isCancelable=true
        }

//        if (Build.VERSION.SDK_INT <= 24) {
//            binding.lockScreen.gone()
//            binding.homeScreen.gone()
//        }

        binding.allScreen.setOnClickListener {
            setWallpaperAll(bitmap)
        }

        binding.lockScreen.setOnClickListener {
            setWallpaperLock(bitmap)
        }

        binding.homeScreen.setOnClickListener {
            setWallpaperHome(bitmap)
        }

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

    companion object {

    }
}