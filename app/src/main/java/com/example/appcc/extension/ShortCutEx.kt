package com.example.appcc.extension

import android.app.Activity
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
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://icon_changer/" + encode))
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

fun createShortcut(
    context: Context,
    applicationInfo: ApplicationInfo,
    bitmap: Bitmap,
    label: String,
    toNextScreen: Boolean
) {
    val encode = URLEncoder.encode(applicationInfo.packageName, "utf-8")
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://icon_changer/" + encode))
    intent.action = Intent.ACTION_VIEW
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(Utils.INTENT_PACKAGE, applicationInfo.packageName)

    if (!ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
        Toast.makeText(context, "Shortcut not supported in this device!", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Create shortcut success!", Toast.LENGTH_SHORT).show()
    }
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createShortcutApiUpper26(context, applicationInfo, bitmap, label, toNextScreen)
        } else {
            createShortcutApiLower26(context, applicationInfo, bitmap, label, toNextScreen)
        }
    } catch (e2: Exception) {
        e2.printStackTrace()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun createShortcutApiUpper26(
    context: Context,
    applicationInfo: ApplicationInfo,
    bitmap: Bitmap,
    label: String,
    toNextScreen: Boolean
) {
    val shortcutManager = context.getSystemService(ShortcutManager::class.java) as ShortcutManager
    if (!shortcutManager.isRequestPinShortcutSupported) {
        Toast.makeText(context, "Shortcut not supported this version", Toast.LENGTH_LONG).show()
        return
    }
    val encode = URLEncoder.encode(applicationInfo.packageName, "utf-8")
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://icon_changer/" + encode))
    intent.action = Intent.ACTION_VIEW
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(Utils.INTENT_PACKAGE, applicationInfo.packageName)
    val intent2 =
        ShortcutInfo.Builder(context, "icon_changer" + System.currentTimeMillis())
            .setIcon(Icon.createWithBitmap(bitmap))
            .setShortLabel(label)
            .setIntent(intent)

    val build = intent2.build()
    val intent3 = Intent(context, MainActivity::class.java)
    intent3.putExtra(Utils.INTENT_BITMAP, bitmap)
    intent3.putExtra(Utils.INTENT_LABEL, label)
    intent3.putExtra("duplicate", false)

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos) //bm is the bitmap object
    val b: ByteArray = baos.toByteArray()
    val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)

    val activity =
        PendingIntent.getBroadcast(context, 100, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
    shortcutManager.requestPinShortcut(build, activity.intentSender)
}

fun createShortcutApiLower26(
    context: Context,
    applicationInfo: ApplicationInfo,
    icon: Bitmap,
    label: String,
    toNextScreen: Boolean
) {
    try {
        val intentShortcut = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, label)
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon)
        val encode = URLEncoder.encode(applicationInfo.packageName, "utf-8")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://icon_changer/" + encode))
        intent.putExtra(Utils.INTENT_PACKAGE, applicationInfo.packageName)

        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent)
        intentShortcut.putExtra("duplicate", false)
        context.sendBroadcast(intentShortcut)

        val baos = ByteArrayOutputStream()
        icon.compress(Bitmap.CompressFormat.PNG, 100, baos) //bm is the bitmap object
        val b: ByteArray = baos.toByteArray()
        val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)
    } catch (unused: Exception) {
        unused.printStackTrace()
    }

}