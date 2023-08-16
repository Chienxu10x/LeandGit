package com.example.appcc.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.data.DataRepository
import com.example.appcc.extension.getBitmapFromAsset
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AppWidget1 : AppWidgetProvider() {
    @Inject
    lateinit var dataRepository: DataRepository

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent);
    }

    private fun updateAppWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_icon_1)
        val currentWidget = dataRepository.getCurrentWidget()

        val bitmap = context.getBitmapFromAsset("Widget/widget$currentWidget/widget_1.webp")
        views.setImageViewBitmap(R.id.iv_app, bitmap)

//        val date = Calendar.getInstance()
//        views.setTextViewText(
//            R.id.tv_date_of_week,
//            date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
//        )
//        views.setTextViewText(
//            R.id.tv_date,
//            date.get(Calendar.DATE).toString()
//        )
//        views.setTextColor(R.id.tv_date_of_week, Color.parseColor(dataRepository.getTextColor1()))
//        views.setTextColor(R.id.tv_date, Color.parseColor(dataRepository.getTextColor2()))

        val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
            .let { intent ->
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
        views.setOnClickPendingIntent(R.id.iv_app, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)

    }

    val handler = Handler(Looper.myLooper()!!)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        updateTime(context, appWidgetManager, appWidgetIds[appWidgetIds.size - 1])
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    fun updateTime(context: Context, widgetManager: AppWidgetManager, widgetId: Int) {
        updateAppWidget(context, widgetManager, widgetId)
        handler.postDelayed({ updateTime(context, widgetManager, widgetId) }, 3600000)
    }

}

