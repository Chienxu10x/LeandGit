package com.example.appcc.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentWidgets1Binding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.viewmodel.WidgetViewModel

class WidgetsFragment1 : BaseFragment() {
    private lateinit var binding: FragmentWidgets1Binding ;
    private val widgetViewModel: WidgetViewModel by activityViewModels()
    var icon1: String = ""
    var icon2: String = ""
    var icon3: String = ""
    var icon4: String = ""
    var icon5: String = ""
    var icon6: String = ""
    var icon7: String = ""
    var font1: String = ""
    var font2: String = ""

    var fontColor1: String = ""
    var fontColor2: String = ""
    var fontColor3: String = ""
    var fontColor4: String = ""
    var currentWidget:Int=0
    override fun bindView() {

          binding.ivWidget1.setOnClickListener {
             activity?.let {act->
                 widgetViewModel.setStringCurrentWidget(icon1)
                 Log.d("TAG", "bindView: 1 "+currentWidget)
                 Log.d("TAG", "bindView: 2 "+icon1)
                 var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                 (act as MainActivity).replaceFragment(detailWidgets)
             }

          }
        binding.ivWidget2.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon2)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
        binding.ivWidget3.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon3)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
        binding.ivWidget4.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon4)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
        binding.ivWidget5.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon5)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
        binding.ivWidget6.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon6)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
        binding.ivWidget7.setOnClickListener {
            activity?.let {act->
                widgetViewModel.setStringCurrentWidget(icon7)
                Log.d("TAG", "bindView: 1 "+currentWidget)
                Log.d("TAG", "bindView: 2 "+icon1)
                var detailWidgets: DetailWidgetsFargment = DetailWidgetsFargment().setUpView()
                (act as MainActivity).replaceFragment(detailWidgets)
            }

        }
//        if (!PurchaseManager.getInstance().isRemovedAds(requireContext())){
//            adView.visibility = View.VISIBLE
//            adView.loadAd (AdRequest.Builder().build())
//        }else{
//            adView.visibility = View.GONE
//        }
    }

    override fun observeData() {

    }
    fun submitDetail(){

    }
    fun reloadView() {
        Glide.with(this).load(icon1.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget1)
        Glide.with(this).load(icon2.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget2)
        Glide.with(this).load(icon3.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget3)
        Glide.with(this).load(icon4.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget4)
        Glide.with(this).load(icon5.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget5)
        Glide.with(this).load(icon6.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget6)
        Glide.with(this).load(icon7.toAssetPath()).placeholder(R.drawable.ic_icon).error(R.drawable.ic_icon).into(binding.ivWidget7)

//        val typeface1 = Typeface.createFromAsset(requireContext().assets, (font1))
//        tv_date_of_week_1.typeface = typeface1
//        tv_hour.typeface = typeface1
//
//        val typeface2 = Typeface.createFromAsset(requireContext().assets, (font2))
//        tv_date.typeface = typeface2
//        tv_date_of_week.typeface = typeface2
//
//
//        tv_date_of_week_1.setTextColor(Color.parseColor(fontColor1))
//        tv_date.setTextColor(Color.parseColor(fontColor2))
//        tv_hour.setTextColor(Color.parseColor(fontColor3))
//        tv_date_of_week.setTextColor(Color.parseColor(fontColor4))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentWidgets1Binding.inflate(layoutInflater)
        return binding.root
    }
}