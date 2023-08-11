package com.example.appcc.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseListAdapter
import com.example.appcc.databinding.ItemViewMainBinding
import com.example.appcc.extension.inflate
import com.example.appcc.model.ContentX
import com.example.appcc.diffcallback.ContentXCallBack
import com.example.appcc.extension.toAssetPath

import com.example.appcc.fragment.FragmentPrivacyPolicy

import com.example.appcc.fragment.FragmentTermOfUse

class RecyclerAdapterTheme(val onItemClick : (item : ContentX) -> Unit)
    : BaseListAdapter<ContentX>(ContentXCallBack()) {

//    private lateinit var binding: ItemViewMainBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_view_main)
        return ThemeItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
//        context?.let { act ->
//            val privacyView: FragmentPrivacyPolicy = FragmentPrivacyPolicy().onSetupView()
//            (act as MainActivity).replaceFragment(privacyView)
//        }
//        holder.itemView.apply {
//            tvTitle.text = item.title
//            tvSubTitle.text = item.subtitle
//
//            Glide.with(this).load(Uri.parse(item.previews[0].toAssetPath())).into(ivIcon1)
//            Glide.with(this).load(Uri.parse(item.previews[1].toAssetPath())).into(ivIcon2)
//            Glide.with(this).load(Uri.parse(item.previews[2].toAssetPath())).into(ivIcon3)
//
//            ivIcon1.setOnClickListener { onItemClick(item) }
//            ivIcon2.setOnClickListener { onItemClick(item) }
//            ivIcon3.setOnClickListener { onItemClick(item) }
//
//            setOnClickListener { onItemClick(item) }
//        }

        holder.itemView.apply {
            val textView : TextView = findViewById(R.id.tvTheme)
            val imageviewMain1 : ImageView = findViewById(R.id.imageview_main1)
            val imageviewMain2 : ImageView = findViewById(R.id.imageview_main2)
            val button : RelativeLayout = findViewById(R.id.buttonTheme)
            textView.text = item.title

            Glide.with(this).load(Uri.parse(item.previews[0].toAssetPath())).into(imageviewMain1)
            Glide.with(this).load(Uri.parse(item.previews[1].toAssetPath())).into(imageviewMain2)
            button.setOnClickListener { onItemClick(item) }

        }
    }

    private inner class ThemeItemHolder(view : View) : RecyclerView.ViewHolder(view)
}

