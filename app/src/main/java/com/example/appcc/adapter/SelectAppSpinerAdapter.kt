package com.example.appcc.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.example.appcc.model.InstalledApp
import com.example.appcc.R


class SelectAppSpinerAdapter(private val context: Context) : SpinnerAdapter {
    private val mAppList = mutableListOf<InstalledApp>()

    fun setList(list: List<InstalledApp>) {
        mAppList.clear()
        mAppList.addAll(list)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
    }

    override fun getCount(): Int {
        return mAppList.size
    }

    override fun getItem(position: Int): InstalledApp {
        return mAppList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return rowView(convertView, position)
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return mAppList.size == 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return rowView(convertView, position)
    }

    private fun rowView(view: View?, position: Int): View {
        val item = getItem(position)

        val holder: ItemViewHoler
        var rowView: View? = view
        if (rowView == null) {
            holder = ItemViewHoler()
            val flater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = flater.inflate(R.layout.item_spinner, null, false)
            holder.textLabel = rowView.findViewById<View>(R.id.tv_name) as TextView
            holder.imageView = rowView.findViewById<View>(R.id.iv_icon) as ImageView
            rowView.tag = holder
        } else {
            holder = rowView.tag as ItemViewHoler
        }
        holder.imageView?.setImageDrawable(item.icon)
        holder.textLabel?.text = item.name

        return rowView!!

    }

    private class ItemViewHoler() {
        var imageView: ImageView? = null
        var textLabel: TextView? = null
    }

}