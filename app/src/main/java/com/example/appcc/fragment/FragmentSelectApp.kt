package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appcc.CreateIconEvent
import com.example.appcc.R
import com.example.appcc.adapter.SelectAppSpinerAdapter
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSelectAppBinding
import com.example.appcc.extension.hideKeyboard
import com.example.appcc.extension.postUIEvent
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.InstalledApp
import com.example.appcc.viewmodel.AppIconViewModel

class FragmentSelectApp : BaseFragment() {
    private val args : FragmentSelectAppArgs by navArgs()
    private var currentPkg : InstalledApp?= null
    private lateinit var adapter : SelectAppSpinerAdapter

    private val appIconViewModel : AppIconViewModel by activityViewModels()

    private lateinit var binding : FragmentSelectAppBinding

    override fun bindView() {
        val imagePath = args.imgPath
        Log.d("TAG", "bindView: " + imagePath)
        Glide.with(this).load(imagePath.toAssetPath()).into(binding.imgApp)
        appIconViewModel.loadListApp(requireContext())
        adapter = SelectAppSpinerAdapter(requireContext())

        binding.tvCreateIcon.setOnClickListener {
            if(binding.edLabel.text.toString() == ""){
                Toast.makeText(requireContext(), "Label mus not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentPkg?.let {
                it.label = binding.edLabel.text.toString()
                postUIEvent(CreateIconEvent.SelectRelatedApp(it))
                requireActivity().hideKeyboard()
                requireActivity().onBackPressed()
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
            requireActivity().onBackPressed()
        }

    }

    override fun observeData() {
        appIconViewModel.listApp.observe(this){

            adapter.setList(it)
            binding.spnSelectApp.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAppBinding.inflate(layoutInflater)
        return binding.root
    }



}