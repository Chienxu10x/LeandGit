package com.example.appcc.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.data.DataRepository
import com.example.appcc.extension.getBitmapFromAsset
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppWidget7 : AppWidgetProvider() {

    @Inject
    lateinit var dataRepository: DataRepository
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    private fun updateAppWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId : Int
    ){
        val views = RemoteViews(context.packageName, R.layout.widget_icon_3)
        val currentWidget = dataRepository.getCurrentWidget()
        val bitmap = context.getBitmapFromAsset("Widget/widget$currentWidget/widget_7.webp")
        views.setImageViewBitmap(R.id.iv_app, bitmap)
        val pendingIntent : PendingIntent = Intent(context, MainActivity::class.java)
            .let { intent ->
                PendingIntent.getActivity(context, 0,intent, PendingIntent.FLAG_IMMUTABLE)
            }
        views.setOnClickPendingIntent(R.id.iv_app, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if(appWidgetIds!!.isNotEmpty()){
            updateAppWidget(context!!, appWidgetManager!!, appWidgetIds[appWidgetIds.size-1])
        }
    }
}