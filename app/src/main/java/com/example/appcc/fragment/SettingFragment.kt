package com.example.appcc.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appcc.R
import com.example.appcc.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_setting, container, false)

        // Access the DrawerLayout from the parent activity


        return rootView
    }

    companion object {

    }
}