package com.example.appcc.extension

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appcc.activity.MainActivity
import com.example.appcc.activity.SuccessActivity
import com.example.appcc.utils.Utils
import java.io.ByteArrayOutputStream
import java.net.URLEncoder
import java.util.*

fun createMultipleShortcut(
    context: Context,
    applicationInfo: ApplicationInfo,
    bitmap: Bitmap,
    label: String,
    toNextScreen: Boolean
) {
    val encode = URLEncoder.encode(applicationInfo.packageName, "utf-8")
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://icon_changer/"+encode))
    intent.action = Intent.ACTION_VIEW
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(Utils.INTENT_PACKAGE, applicationInfo.packageName)
    val shortcutInfo = ShortcutInfoCompat.Builder(context, UUID.randomUUID().toString())
        .setIntent(intent) // !!! intent's action must be set on oreo
        .setShortLabel(label)
        .setIcon(IconCompat.createWithBitmap(bitmap))
        .build()
    ShortcutManagerCompat.requestPinShortcut(
        context, shortcutInfo, null
    )
    Toast.makeText(context, "Create shortcut success!", Toast.LENGTH_SHORT).show()
}

