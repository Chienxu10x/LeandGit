package com.example.appcc.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.databinding.ItemViewMainBinding
import com.example.appcc.databinding.ItemViewTimelineBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX

class RecyclerAdapterTimeline(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterTimeline.ViewHolder>() {
    private var list: MutableList<ContentX> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Tạo ViewHolder từ layout item_layout.xml
        val binding =
            ItemViewTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    fun updateList(list: MutableList<ContentX>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    inner class ViewHolder(val binding: ItemViewTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContentX) {
            Glide.with(context).load(Uri.parse(item.previews!![0].toAssetPath())).into(binding.imageTimeline)
            Glide.with(context).load(Uri.parse(item.previews!![1].toAssetPath())).into(binding.imageTimeline2)
        }
        // Định nghĩa các view trong item layout
    }

    override fun getItemCount(): Int {
        // Trả về số lượng phần tử trong danh sách
        return list.size
    }
}