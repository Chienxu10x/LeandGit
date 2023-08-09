package com.example.appcc.widget

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.widget.RemoteViews
import com.example.appcc.AlarmRecive
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.data.DataRepository
import com.example.appcc.extension.getBitmapFromAsset
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AppWidget4 : AppWidgetProvider() {
    @Inject
    lateinit var dataRepository: DataRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        setAlarm(context!!)
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_icon_4)
        // đặt ảnh
        val currentWidget = dataRepository.getCurrentWidget()
        val bitmap = context.getBitmapFromAsset("Widget/widget$currentWidget/widget_2.webp")
        views.setImageViewBitmap(R.id.iv_app, bitmap)

        // đặt giờ
        val date: Calendar = Calendar.getInstance()
        views.setTextViewText(
            R.id.tv_hour, SimpleDateFormat("HH:mm").format(date.time)
        )
        views.setTextViewText(
            R.id.tv_date_of_week,
            date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
        )
        views.setTextColor(R.id.tv_hour, Color.parseColor(dataRepository.getTextColor3()))
        views.setTextColor(R.id.tv_date_of_week, Color.parseColor(dataRepository.getTextColor4()))

        //click widget home_screen chạy đến home
        val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
            .let { intent ->
                PendingIntent.getActivity(context, 0, intent, 0)
            }
        views.setOnClickPendingIntent(R.id.iv_app, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)

    }


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray
    ) {
        setAlarm(context)
        appWidgetIds.forEach {
            updateTime(context, appWidgetManager!!, appWidgetIds[appWidgetIds.size - 1])
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    val handler = Handler(Looper.myLooper()!!)
    fun updateTime(context: Context, widgetManager: AppWidgetManager, widgetId: Int) {
        updateAppWidget(context, widgetManager, widgetId)
        handler.postDelayed({ updateTime(context, widgetManager, widgetId) }, 30000)
    }


    fun setAlarm(context: Context) {
        var alarmMgr: AlarmManager? = null
        val alarmIntent: PendingIntent
        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmRecive::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        //lặp lại sau mỗi 1p
        alarmMgr.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000 * 60,
            alarmIntent
        )
    }
}



