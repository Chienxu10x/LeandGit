package com.example.appcc.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentDetailWidget2Binding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX
import com.example.appcc.model.WidgetXX
import com.example.appcc.viewmodel.WidgetViewModel


class DetailWidgetsFragment(contentX: ContentX) : BaseFragment() {

    private lateinit var binding: FragmentDetailWidget2Binding
    val contentX = contentX
    private val widgetViewModel: WidgetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailWidget2Binding.inflate(layoutInflater)
        return binding.root
    }
    override fun bindView() {
        Glide.with(this).load(Uri.parse(contentX.previews!![3].toAssetPath())).into(binding.ivWidget2)
        Glide.with(this).load(Uri.parse(contentX.previews!![4].toAssetPath())).into(binding.ivWidget1)
        Glide.with(this).load(Uri.parse(contentX.previews!![5].toAssetPath())).into(binding.ivWidget3)
        Glide.with(this).load(Uri.parse(contentX.previews!![6].toAssetPath())).into(binding.ivWidget4)
        Glide.with(this).load(Uri.parse(contentX.previews!![7].toAssetPath())).into(binding.ivWidget5)
        Glide.with(this).load(Uri.parse(contentX.previews!![8].toAssetPath())).into(binding.ivWidget6)
        Glide.with(this).load(Uri.parse(contentX.previews!![9].toAssetPath())).into(binding.ivWidget7)
        binding.btnSetWid.setOnClickListener{
            widgetViewModel.setCurrentWidget(widgetViewModel.getCurrentWidgetPosion())
            Toast.makeText(requireContext(),"OK", Toast.LENGTH_SHORT).show();

        }
    }



    override fun observeData() {
    }

}