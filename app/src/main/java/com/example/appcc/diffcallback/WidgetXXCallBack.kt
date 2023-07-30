package com.example.kittheme.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.kittheme.model.WidgetXX

class WidgetXXCallBack() : DiffUtil.ItemCallback<WidgetXX>() {
    override fun areItemsTheSame(oldItem: WidgetXX, newItem: WidgetXX): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: WidgetXX, newItem: WidgetXX): Boolean {
        return (oldItem.equals(newItem))
    }
}