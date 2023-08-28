package com.example.appcc.fragment

import android.content.res.AssetManager
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.AdapterStyle
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentTypeBinding
import java.io.IOException

class FragmentType : BaseFragment() {

    private lateinit var binding: FragmentTypeBinding

    override fun bindView() {
        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }

    override fun observeData() {
        listStyle()
    }

    fun listStyle(){
//        val assets: AssetManager = resources.assets
        val list : MutableList<String> = arrayListOf()
//        try {
//            val fontFiles: Array<String> = assets.list("font") ?: arrayOf()
//
//            for (fontFile in fontFiles) {
//                val fontPath = "font/$fontFile"
//                list.add(fontPath)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
        val adapterStyle = AdapterStyle(requireContext(), list)
        val string:CharSequence = "Well"

        binding.editTextStyle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the TextView when text changes
                if (s==null){
                    adapterStyle.UpdateString(string)
                }else{
                    adapterStyle.UpdateString(s)
                }

            }
            override fun afterTextChanged(s: Editable?) {
                // No action needed after text changes
            }
        })

        binding.listStyle.layoutManager = LinearLayoutManager(requireContext())
        binding.listStyle.adapter = adapterStyle

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTypeBinding.inflate(layoutInflater)
        return binding.root;
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }

}