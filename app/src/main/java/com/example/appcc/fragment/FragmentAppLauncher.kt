package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.appcc.R
import com.example.appcc.adapter.MyAppLauncherAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentAppLauncherBinding
import com.example.appcc.databinding.FragmentThemesBinding
import com.example.appcc.model.MyAppIcon
import com.example.appcc.viewmodel.ShortcutViewModel

class FragmentAppLauncher : BaseFragment() {
    private lateinit var binding: FragmentAppLauncherBinding
    private val shortcutViewModel: ShortcutViewModel by activityViewModels()
    val someList = mutableListOf<MyAppIcon>()
    val arg: FragmentAppLauncherArgs by navArgs()
    private var changeIconPosition = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppLauncherBinding.inflate(layoutInflater)
        return binding.root
    }

    val adapter = MyAppLauncherAdapter { position, flag ->
        when (flag) {

        }
    }
    override fun bindView() {
        val contentX = arg.contentx
        Log.d("TAG", "bindView: "+ contentX)

    }

    override fun observeData() {
    }



}