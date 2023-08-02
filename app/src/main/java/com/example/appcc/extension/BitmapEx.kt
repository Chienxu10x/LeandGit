package com.example.kittheme.extension

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.io.InputStream

// đọc file ảnh từ assets đối với widget
fun Context.getBitmapFromAsset(filePath: String?): Bitmap? {
    val assetManager: AssetManager = getAssets()
    val istr: InputStream
    var bitmap: Bitmap? = null
    try {
        istr = assetManager.open(filePath!!)
        bitmap = BitmapFactory.decodeStream(istr)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return bitmap
}