package com.example.appcc.adapter

import android.location.GnssAntennaInfo.Listener
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.Content
import com.example.appcc.model.ContentX

class RecyclerAdapterTheme2(var contents: List<ContentX>, val listener: ClickListener) :
    RecyclerView.Adapter<RecyclerAdapterTheme2.ViewHolder>() {

    interface ClickListener {
        fun onClickListener(item: ContentX)
    }

    fun updateData(newItems: List<ContentX>) {
        contents  = newItems
        notifyDataSetChanged() // Notify RecyclerView about the changes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = contents[position]
        holder.bind(data, position)

        holder.itemView.apply {
            val imageviewMain1 : ImageView = findViewById(R.id.imageview_main1)
            val imageviewMain2 : ImageView = findViewById(R.id.imageview_main2)
            Glide.with(this).load(Uri.parse(data.previews!![0].toAssetPath())).into(imageviewMain1)
            Glide.with(this).load(Uri.parse(data.previews!![1].toAssetPath())).into(imageviewMain2)
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
            itemView.findViewById<TextView>(R.id.tvTheme).text = data.title
//            itemView.findViewById<RelativeLayout>(R.id.buttonTheme) = data.
            this.contentItem = data
            this.currentPosition = position

        }
    }


}