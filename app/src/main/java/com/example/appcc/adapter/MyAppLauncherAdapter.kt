package com.example.appcc.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.base.BaseListAdapter
import com.example.appcc.diffcallback.MyAppIconDiffCallBack
import com.example.appcc.extension.gone
import com.example.appcc.extension.inflate
import com.example.appcc.extension.toAssetPath
import com.example.appcc.extension.visibble
import com.example.appcc.model.MyAppIcon


class MyAppLauncherAdapter(val onItemClick: (position: Int, flag: Int) -> Unit,) :
    BaseListAdapter<MyAppIcon>(
        MyAppIconDiffCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(R.layout.item_view_icon)
        return AppViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            val iv_icon_material : ImageView = findViewById(R.id.iv_icon_material)
            val iv_add : ImageView = findViewById(R.id.iv_add)
            val iv_remove : ImageView = findViewById(R.id.iv_remove)
            val btn_check : CheckBox = findViewById(R.id.btn_check)
            val ln_install : RelativeLayout = findViewById(R.id.ln_install)
            val rl_install : RelativeLayout = findViewById(R.id.rl_install)


            Glide.with(this).load(item.icon.toAssetPath()).into(iv_icon_material)
            if (item.appIcon != null) {
                Glide.with(this).load(item.appIcon).into(iv_add)
                iv_remove.visibble()
                btn_check.isEnabled = true
                btn_check.isChecked = false
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ln_install.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
                }
            } else {
                Glide.with(this).load(R.drawable.ic_plus_blank).into(iv_add)
                iv_remove.gone()
            }

            iv_remove.setOnClickListener {
                onItemClick(position, FLAG_REMOVE)
                notifyDataSetChanged()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ln_install.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.gray_1)))
                }
                btn_check.isEnabled = false
                btn_check.isChecked = false
            }
            iv_add.setOnClickListener {
                onItemClick(position, FLAG_ADD_ICON)
            }

            rl_install.setOnClickListener {
                onItemClick(position, FLAG_ADD_ICON)
            }


            ln_install.setOnClickListener {
                onItemClick(position, FLAG_INSTALL)
            }


            btn_check.setOnClickListener {
                val isChecked = btn_check.isChecked
                item.check = isChecked
                onItemClick(position, FLAG_COUNT)
            }

            if (item.check) {
                btn_check.isChecked = true
            }
            if (item.check == false) {
                btn_check.isChecked = false
            }

        }
    }

    companion object {
        val FLAG_ADD_ICON = 1
        val FLAG_INSTALL = 2
        val FLAG_REMOVE = 3
        val FLAG_COUNT = 4
    }

    inner class AppViewHolder(item: View) : RecyclerView.ViewHolder(item)
}