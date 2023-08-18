package com.example.appcc.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentThemeDetailBinding
import com.example.appcc.dialog.ThemeDetailDialog
import com.example.appcc.extension.navigateTo
import com.example.appcc.extension.toAssetPath
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel

class FragmentThemeDetail : BaseFragment() {
    private lateinit var binding: FragmentThemeDetailBinding

    private val authViewModel: AuthViewModel by viewModels()
    val arg: ThemesFragmentArgs by navArgs()
    //    var item = mutableListOf<ContentX>()
//    private var item: Observer<ContentX>? = null
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
        
//        Log.d("TAGV", "bindView: " + item)
        Glide.with(this).load(Uri.parse(item.previews!![0].toAssetPath()))
            .into(binding.imageThemeItem)
        binding.apply {
            btnCustom.setOnClickListener {
                val dialogTheme = ThemeDetailDialog()
                dialogTheme.isCancelable = false
                dialogTheme.show(childFragmentManager, DIALOG_THEME)
            }
            binding.btnGetTheme.setOnClickListener {
//            toGetTheme(item)
                activity?.let { act ->
                    var fragmentGetTheme: FragmentGetTheme = FragmentGetTheme(item).setUpView()
                    (act as MainActivity).replaceFragment(fragmentGetTheme)
                }
            }
            binding.imgshare.setOnClickListener {
                Toast.makeText(requireActivity(),"click",Toast.LENGTH_SHORT).show()
                authViewModel.addTimeline(item)
                authViewModel.addTimeline.observe(viewLifecycleOwner) { state ->
                    when(state){
                        is UiState.Loading -> {
//                            binding.btnProgressAr.show()
//                            binding.button.text = ""
                        }
                        is UiState.Failure -> {
//                            binding.btnProgressAr.hide()
//                            binding.button.text = "Create"
                           Toast.makeText(requireActivity(),state.error,Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Success -> {
                            Toast.makeText(requireActivity(),state.data,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    override fun observeData() {

    }
//    override fun baseBackPressed() {
//        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Log.d("aaaa", "handleOnBackPressed: aaaa")
//                    onBackPressed()
//                }
//            })
//
//    }
//    private fun onBackPressed() {
//        activity?.let { act ->
//            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            showInBaseNavigationView()
//            showIntoolBar()
//
////            (act as MainActivity).removeFragment(this)
//        }
//    }
    fun onSetupView(): FragmentThemeDetail {
        return FragmentThemeDetail()

    }

    fun toGetTheme(){

    }

    companion object {
        private const val DIALOG_THEME = "DIALOG_THEME"
    }
}