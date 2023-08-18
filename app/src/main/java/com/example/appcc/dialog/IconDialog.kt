package com.example.appcc.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.appcc.R
import com.example.appcc.adapter.RecyclerAdapterIcon
import com.example.appcc.databinding.FragmentIconDialogBinding
import com.example.appcc.databinding.FragmentWallpaperDialogFargmentBinding
import com.example.appcc.viewmodel.IconsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class IconDialog : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentIconDialogBinding
    private val iconViewModel : IconsViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIconDialogBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        isCancelable=true
        return binding.root

    }
    private val recyclerAdapterIcon = RecyclerAdapterIcon{}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iconViewModel.currentTheme.observe(this){
            recyclerAdapterIcon.submitList(it.content)

            binding.recyclerviewIcon.adapter = recyclerAdapterIcon
        }
        iconViewModel.allTheme.observe(this){
            iconViewModel.getThemeByFilter(0)
        }
        iconViewModel.loadAllResource(requireContext())

    }
}