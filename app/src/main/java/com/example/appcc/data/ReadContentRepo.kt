
package com.example.kittheme.data

import android.content.Context
import com.example.appcc.data.ResourceUtils
import com.example.appcc.model.Theme
import com.example.appcc.model.Widget
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class ReadContentRepo @Inject constructor(
) {
    fun readDataIcon(context: Context): Theme {
        val res = ResourceUtils.loadData(context, "contents.json")
        return Gson().fromJson(res, Theme::class.java)
    }

    fun readDataWidget(context: Context): Widget {
        val res = ResourceUtils.loadData(context, "widget.json")
        return Gson().fromJson(res, Widget::class.java)
    }
}