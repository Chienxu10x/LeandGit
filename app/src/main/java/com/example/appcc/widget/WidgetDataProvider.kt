package com.example.appcc.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.appcc.R

class WidgetDataProvider(val context: Context?) : RemoteViewsService.RemoteViewsFactory {

    var mContext: Context? = null
    override fun onCreate() {
        initData()
    }

    override fun onDataSetChanged() {
        initData()
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
       return 1
    }

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(
            mContext!!.packageName,
            R.layout.widget_icon_2
        )
        return view
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
    private fun initData() {

    }
}