package com.example.appcc.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.appcc.model.ContentX

class ContentXCallBack() : DiffUtil.ItemCallback<ContentX>() {
    override fun areItemsTheSame(oldItem: ContentX, newItem: ContentX): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ContentX, newItem: ContentX): Boolean {
        return (oldItem.equals(newItem))
    }
}