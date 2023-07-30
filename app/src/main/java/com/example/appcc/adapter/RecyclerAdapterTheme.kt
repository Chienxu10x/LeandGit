package com.example.appcc.adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R


class RecyclerAdapterTheme(private val dataList: List<String> ):
    RecyclerView.Adapter<RecyclerAdapterTheme.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Định nghĩa các view trong item layout
        val textTheme1: TextView = itemView.findViewById(R.id.tvTheme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Tạo ViewHolder từ layout item_layout.xml
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Gắn dữ liệu vào các view bên trong ViewHolder
        holder.textTheme1.text = dataList[position]
    }

    override fun getItemCount(): Int {
        // Trả về số lượng phần tử trong danh sách
        return dataList.size
    }
}