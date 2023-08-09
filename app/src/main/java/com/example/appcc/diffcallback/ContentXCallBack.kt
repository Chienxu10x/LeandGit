package com.example.appcc.diffcallback

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.appcc.model.ContentX

class ContentXCallBack() : DiffUtil.ItemCallback<ContentX>() {
    override fun areItemsTheSame(oldItem: ContentX, newItem: ContentX): Boolean {
        Log.d("TAG", "areItemsTheSame: ")
        return oldItem.title == newItem.title

    }

    override fun areContentsTheSame(oldItem: ContentX, newItem: ContentX): Boolean {
        Log.d("TAG", "areContentsTheSame: ")
        return (oldItem.equals(newItem))
    }
}