package com.example.kittheme.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.kittheme.model.MyAppIcon

class MyAppIconDiffCallBack(): DiffUtil.ItemCallback<MyAppIcon>() {
    override fun areItemsTheSame(oldItem: MyAppIcon, newItem: MyAppIcon): Boolean {
        return oldItem.icon == newItem.icon
    }

    override fun areContentsTheSame(oldItem: MyAppIcon, newItem: MyAppIcon): Boolean {
        return (oldItem.equals(newItem))
    }
}