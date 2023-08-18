package com.example.appcc.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcc.R
import com.example.appcc.databinding.FragmentThemeDetailDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ThemeDetailDialog : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentThemeDetailDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style. BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentThemeDetailDialogBinding.inflate(layoutInflater,container,false)
        isCancelable=true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLister()
    }

    private fun initLister() {
        binding.btnWallpapers.setOnClickListener{
            isCancelable=true
            val dialogWallpaper =WallpaperDialog()
            dialogWallpaper.isCancelable=false
            dialogWallpaper.show(childFragmentManager,DIALOG_WALLPAPER)
        }

        binding.btnIcons.setOnClickListener{
            isCancelable = true
            val iconDialog = IconDialog()
            iconDialog.isCancelable = false
            iconDialog.show(childFragmentManager, ICON_DIALOG)
        }
    }

    companion object {
        private const val DIALOG_WALLPAPER = "DIALOG_WALLPAPER"
        private const val ICON_DIALOG = "ICON_DIALOG"
    }
}