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
import com.example.appcc.activity.MainActivity
import com.example.appcc.databinding.ItemIconsTimelineBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.fragment.FragmentIconDetail
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX

class RecyclerAdapterTimelineIcon(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterTimelineIcon.ViewHolder>() {
    private var list: MutableList<CommentModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Tạo ViewHolder từ layout item_layout.xml
        val binding =
            ItemIconsTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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

    inner class ViewHolder(val binding: ItemIconsTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentModel,position: Int) {
            Glide.with(context)
                .load(item.avatar).error(R.drawable.ic_account)
                .into(binding.imageViewTimelineFace)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[0].toAssetPath())).into(binding.iconImageview1)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[1].toAssetPath())).into(binding.iconImageview2)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[2].toAssetPath())).into(binding.iconImageview3)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[3].toAssetPath())).into(binding.iconImageview4)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[4].toAssetPath())).into(binding.iconImageview5)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[5].toAssetPath())).into(binding.iconImageview6)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[6].toAssetPath())).into(binding.iconImageview7)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[7].toAssetPath())).into(binding.iconImageview8)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[8].toAssetPath())).into(binding.iconImageview9)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[9].toAssetPath())).into(binding.iconImageview10)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[10].toAssetPath())).into(binding.iconImageview11)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[11].toAssetPath())).into(binding.iconImageview12)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[12].toAssetPath())).into(binding.iconImageview13)
            Glide.with(context).load(Uri.parse(item.contentX!!.icon[13].toAssetPath())).into(binding.iconImageview14)
            binding.tvComment.setText(item.comnet)
            binding.textViewNameTimeline.setText(item.userName)
            binding.btnGetTheme2.setOnClickListener {
                context.let{act->
                    val fargmentGettheme=FragmentIconDetail(item.contentX!!).setUpView()
                    (act as MainActivity).replaceFragment(fargmentGettheme)
                }
            }
        }
        // Định nghĩa các view trong item layout
    }

    override fun getItemCount(): Int {
        // Trả về số lượng phần tử trong danh sách
        return list.size
    }
}