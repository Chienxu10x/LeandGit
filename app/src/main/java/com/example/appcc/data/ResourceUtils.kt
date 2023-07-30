package com.example.kittheme.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
}