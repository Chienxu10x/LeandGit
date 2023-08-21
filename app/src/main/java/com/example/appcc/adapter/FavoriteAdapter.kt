package com.example.appcc.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.activity.MainActivity
import com.example.appcc.databinding.ItemViewMainBinding
import com.example.appcc.extension.toAssetPath
import com.example.appcc.fragment.FragmentIconDetail
import com.example.appcc.model.ContentX

class FavoriteAdapter(private val context: Context) : RecyclerView.Adapter<FavoriteAdapter.viewHodelFavorite>() {
    private var list: MutableList<ContentX> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHodelFavorite {
        val binding=ItemViewMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHodelFavorite(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHodelFavorite, position: Int) {
        val item=list[position]
        holder.bind(item)
    }
    fun updateFavorite( list: MutableList<ContentX>){
        this.list=list
        notifyDataSetChanged()
    }
    inner class viewHodelFavorite(val binding: ItemViewMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ContentX){
            binding.tvTheme.setText(item.title)
            Glide.with(context).load(Uri.parse(item.previews!![0].toAssetPath())).into(binding.imageviewMain1)
            Glide.with(context).load(Uri.parse(item.previews!![1].toAssetPath())).into(binding.imageviewMain2)
            binding.buttonTheme.setOnClickListener{
                context.let { act->
                    val fragmentIconDetail =FragmentIconDetail(item).setUpView()
                    (act as MainActivity).replaceFragment(fragmentIconDetail)
                }
            }
        }

    }
}