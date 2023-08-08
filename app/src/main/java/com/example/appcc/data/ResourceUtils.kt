package com.example.appcc.data

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.IOException

object ResourceUtils {
    fun loadData(context: Context, inFile: String): String {
        var tContents = ""

        try {
            val stream = context.assets.open(inFile);

            val size = stream.available();
            val buffer = ByteArray(size)
            stream.read(buffer);
            stream.close();
            tContents = String(buffer);
        } catch (e: IOException) {
            // Handle exceptions here
        }

        return tContents;
    }

    fun getBitmapFromAsset(context: Context, path: String): Bitmap =
        context.assets.open(path).use { BitmapFactory.decodeStream(it) }
//    fun openMail(context: Context) {
//        val intent = Intent(Intent.ACTION_SENDTO)
//        intent.data = Uri.parse("mailto:")
//        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL))
//        startActivity(context, intent)
//    }
}