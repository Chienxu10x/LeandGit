package com.example.appcc.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.databinding.ItemCommentThemedetailBinding
import com.example.appcc.databinding.ItemIconsTimelineBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.fragment.FragmentIconDetail
import com.example.appcc.model.CommentModel

class AdapterCommentTheme(private val context: Context) :
    RecyclerView.Adapter<AdapterCommentTheme.ViewHolder>() {
    private var list: MutableList<CommentModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Tạo ViewHolder từ layout item_layout.xml
        val binding =
            ItemCommentThemedetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    fun updateList(list: MutableList<CommentModel>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item,position)

    }

    inner class ViewHolder(val binding: ItemCommentThemedetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentModel, position: Int) {
            Glide.with(context)
                .load(item.avatar).error(R.drawable.ic_account)
                .into(binding.imageViewTimelineFace)
            binding.tvComment.setText(item.comnet)
            binding.textViewNameTimeline.setText(item.userName)
        }
        // Định nghĩa các view trong item layout
    }

    override fun getItemCount(): Int {
        // Trả về số lượng phần tử trong danh sách
        return list.size
    }
}