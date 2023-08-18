package com.example.appcc.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.appcc.R
import com.example.appcc.adapter.RecyclerAdapterWallpaper
import com.example.appcc.databinding.FragmentWallpaperDialogFargmentBinding
import com.example.appcc.viewmodel.IconsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WallpaperDialog : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentWallpaperDialogFargmentBinding
    private val iconViewModel : IconsViewModel by activityViewModels()
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
    private val recyclerAdapterWallpaper = RecyclerAdapterWallpaper{
//        activity?.let { act ->
//            val privacyView: FragmentThemeDetail = FragmentThemeDetail().onSetupView()
//            (act as MainActivity).replaceFragment(privacyView)
//        }
        Toast.makeText(requireContext(), "Oke", Toast.LENGTH_SHORT).show()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: aaaaaaaaaaaaaaaaaaaa")
        iconViewModel.currentTheme.observe(this){
            Log.d("TAG", "observeData: "+"a0")
            recyclerAdapterWallpaper.submitList(it.content)
            Log.d("TAG", "observeData: "+it.content.get(0).wallpaper)
            binding.RecyWallpaper.adapter = recyclerAdapterWallpaper
        }
        
        iconViewModel.allTheme.observe(this){
            Log.d("TAG", "observeData: "+"a1")
            iconViewModel.getThemeByFilter(0)
//            it.contents.forEach{
//                val tab = binding.tabMenu.newTab()
//                tab.text = it.title
////                Log.d("TAG", "observeData: "+it.content.get(0).previews)
//                binding.tabMenu.addTab(tab)
//            }
        }
        iconViewModel.loadAllResource(requireContext())
    }

    private fun initLister() {

    }
}