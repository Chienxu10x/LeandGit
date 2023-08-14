package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appcc.CreateIconEvent
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.SelectAppSpinerAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSelectAppBinding
import com.example.appcc.extension.hideKeyboard
import com.example.appcc.extension.postUIEvent
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX
import com.example.appcc.model.InstalledApp
import com.example.appcc.viewmodel.AppIconViewModel

class FragmentSelectApp2(string: String) : BaseFragment() {
//    private val args : FragmentSelectAppArgs by navArgs()
    private var currentPkg : InstalledApp?= null
    private lateinit var adapter : SelectAppSpinerAdapter
    private val appIconViewModel : AppIconViewModel by activityViewModels()
    private lateinit var binding : FragmentSelectAppBinding
    val imagePath =  string
    override fun bindView() {
//        val imagePath = args.imgPath
        Log.d("TAGIM", "bindView: " + imagePath)
        Glide.with(this).load(imagePath.toAssetPath()).into(binding.imgApp)
        appIconViewModel.loadListApp(requireContext())
        adapter = SelectAppSpinerAdapter(requireContext())

        binding.tvCreateIcon.setOnClickListener {
//            if(binding.edLabel.text.toString() == ""){
//                Toast.makeText(requireContext(), "Label mus not be empty", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            currentPkg?.let {
                it.label = binding.edLabel.text.toString()
                postUIEvent(CreateIconEvent.SelectRelatedApp(it))
                requireActivity().hideKeyboard()
                onBackPress()
            }
        }

        binding.spnSelectApp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentPkg = adapter.getItem(position)
                binding.edLabel.setText(adapter.getItem(position).name)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

    }

    override fun observeData() {
        appIconViewModel.listApp.observe(this, Observer {
            adapter.setList(it)
            binding.spnSelectApp.adapter = adapter
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAppBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setUpView() : FragmentSelectApp2{
        return FragmentSelectApp2(imagePath)
    }

    private fun onBackPress() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (act as MainActivity).removeFragment(this)
        }
    }





}