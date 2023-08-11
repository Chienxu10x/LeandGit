package com.example.appcc.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcc.R
import com.example.appcc.databinding.FragmentWallpaperDialogFargmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WallpaperDialogFargment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentWallpaperDialogFargmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentWallpaperDialogFargmentBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        isCancelable=true
        return binding.root
    }

    companion object {

    }
}