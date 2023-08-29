package com.example.appcc.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.databinding.ItemViewMainBinding
import com.example.appcc.databinding.ItemViewTimelineBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.fragment.FragmentGetTheme
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX

class RecyclerAdapterTimeline(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterTimeline.ViewHolder>() {
    private var list: MutableList<CommentModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Tạo ViewHolder từ layout item_layout.xml
        val binding =
            ItemViewTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<CommentModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)

    }

    inner class ViewHolder(val binding: ItemViewTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentModel, position: Int) {
            Glide.with(context)
                .load(Uri.parse(item.contentX?.previews!![0].toAssetPath()))
                .into(binding.imageTimeline)
      //      Log.d("TAG", "bind: " + item.name)
            Glide.with(context)
                .load(item.avatar).error(R.drawable.ic_account)
                .into(binding.imageViewTimelineFace)
            Glide.with(context)
                .load(Uri.parse(item.contentX?.previews!![1].toAssetPath()))
                .into(binding.imageTimeline2)
            binding.textViewNameTimeline.setText(item.userName)
            binding.tvComment.setText(item.comnet)
            binding.btnGetTheme1.setOnClickListener {
                context.let { act ->
                    val fragmentGetTheme =
                        FragmentGetTheme(item.contentX!!).setUpView()
                    (act as MainActivity).replaceFragment(fragmentGetTheme)
                }
            }
        }


        // Định nghĩa các view trong item layout
    }

    override fun getItemCount(): Int {
        if (list!=null){
            return list.size
        }
        // Trả về số lượng phần tử trong danh sách
        return 0
    }
}