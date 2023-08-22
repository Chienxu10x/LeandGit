package com.example.appcc.adapter

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX


class AdapterWallPaper(var contents: List<ContentX>, val listener: ClickListener) :
    RecyclerView.Adapter<AdapterWallPaper.ViewHolder>() {

    interface ClickListener {
        fun onClickListener(item: ContentX)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = contents[position]
        holder.bind(data, position)

        holder.itemView.apply {
            val imageviewWallPaper : ImageView = findViewById(R.id.image_wallpaper)
            Glide.with(this).load(Uri.parse(data.wallpaper.toAssetPath())).into(imageviewWallPaper)
        }
        holder.itemView.setOnClickListener{
            listener.onClickListener(data)
        }
    }
    override fun getItemCount(): Int {
        return contents.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var contentItem: ContentX? = null
        private var currentPosition: Int = 0
        fun bind(data: ContentX, position: Int) {
            this.contentItem = data
            this.currentPosition = position

        }
    }


}