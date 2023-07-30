package com.example.appcc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R

class RecyclerAdapterTimeline(private val dataList: List<String>) :
    RecyclerView.Adapter<RecyclerAdapterTimeline.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // Định nghĩa các view trong item layout
            val textTheme: TextView = itemView.findViewById(R.id.textViewNameTimeline)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Tạo ViewHolder từ layout item_layout.xml
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_view_timeline, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Gắn dữ liệu vào các view bên trong ViewHolder
            holder.textTheme.text = dataList[position]
        }

        override fun getItemCount(): Int {
            // Trả về số lượng phần tử trong danh sách
            return dataList.size
        }
}