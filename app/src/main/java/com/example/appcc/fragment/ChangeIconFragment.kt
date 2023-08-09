package com.example.appcc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentChangeIconBinding
import com.example.appcc.databinding.FragmentThemesBinding
import com.example.appcc.model.MyAppIcon
import com.example.appcc.viewmodel.ShortcutViewModel
import java.util.*

class ChangeIconFragment : BaseFragment() {

    private lateinit var binding: FragmentChangeIconBinding


    override fun bindView() {

    }

    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeIconBinding.inflate(layoutInflater)
        return binding.root
    }

}