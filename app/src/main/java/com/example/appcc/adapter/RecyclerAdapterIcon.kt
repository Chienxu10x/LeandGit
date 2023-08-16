package com.example.appcc.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.base.BaseListAdapter
import com.example.appcc.diffcallback.ContentXCallBack
import com.example.appcc.extension.inflate
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.ContentX

class RecyclerAdapterIcon(val onItemClick : (item : ContentX) -> Unit)
    : BaseListAdapter<ContentX>(ContentXCallBack()) {
    //    private lateinit var binding: ItemViewMainBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_icons)
        return ThemeItemHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            val textView: TextView = findViewById(R.id.textIcon)
            val imageIcon1: ImageView = findViewById(R.id.icon_imageview1)
            val imageIcon2: ImageView = findViewById(R.id.icon_imageview2)
            val imageIcon3: ImageView = findViewById(R.id.icon_imageview3)
            val imageIcon4: ImageView = findViewById(R.id.icon_imageview4)
            val imageIcon5: ImageView = findViewById(R.id.icon_imageview5)
            val imageIcon6: ImageView = findViewById(R.id.icon_imageview6)
            val imageIcon7: ImageView = findViewById(R.id.icon_imageview7)
            val imageIcon8: ImageView = findViewById(R.id.icon_imageview8)
            val imageIcon9: ImageView = findViewById(R.id.icon_imageview9)
            val imageIcon10: ImageView = findViewById(R.id.icon_imageview10)
            val imageIcon11: ImageView = findViewById(R.id.icon_imageview11)
            val imageIcon12: ImageView = findViewById(R.id.icon_imageview12)
            val imageIcon13: ImageView = findViewById(R.id.icon_imageview13)
            val imageIcon14: ImageView = findViewById(R.id.icon_imageview14)

            textView.text = item.title


            Glide.with(this).load(Uri.parse(item.icon[0].toAssetPath())).into(imageIcon1)
            Glide.with(this).load(Uri.parse(item.icon[1].toAssetPath())).into(imageIcon2)
            Glide.with(this).load(Uri.parse(item.icon[2].toAssetPath())).into(imageIcon3)
            Glide.with(this).load(Uri.parse(item.icon[3].toAssetPath())).into(imageIcon4)
            Glide.with(this).load(Uri.parse(item.icon[4].toAssetPath())).into(imageIcon5)
            Glide.with(this).load(Uri.parse(item.icon[5].toAssetPath())).into(imageIcon6)
            Glide.with(this).load(Uri.parse(item.icon[6].toAssetPath())).into(imageIcon7)
            Glide.with(this).load(Uri.parse(item.icon[7].toAssetPath())).into(imageIcon8)
            Glide.with(this).load(Uri.parse(item.icon[8].toAssetPath())).into(imageIcon9)
            Glide.with(this).load(Uri.parse(item.icon[9].toAssetPath())).into(imageIcon10)
            Glide.with(this).load(Uri.parse(item.icon[10].toAssetPath())).into(imageIcon11)
            Glide.with(this).load(Uri.parse(item.icon[11].toAssetPath())).into(imageIcon12)
            Glide.with(this).load(Uri.parse(item.icon[12].toAssetPath())).into(imageIcon13)
            Glide.with(this).load(Uri.parse(item.icon[13].toAssetPath())).into(imageIcon14)
            setOnClickListener {
                onItemClick(item)

            }




        }
    }

    private inner class ThemeItemHolder(view: View) : RecyclerView.ViewHolder(view)


}
