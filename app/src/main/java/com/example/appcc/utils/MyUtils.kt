package com.example.appcc.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.example.appcc.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern


private val SUBJECT: String = "Aesthetic Kit"

fun getTotalRam(): Double {
    var reader: RandomAccessFile? = null
    var load: String? = null
    val twoDecimalForm = DecimalFormat("#.##")
    var totRam = 0.0
    var ramGB = 0.0
    var lastValue = ""
    try {
        reader = RandomAccessFile("/proc/meminfo", "r")
        load = reader.readLine()

        // Get the Number value from the string
        val p: Pattern = Pattern.compile("(\\d+)")
        val m: Matcher = p.matcher(load)
        var value = ""
        while (m.find()) {
            value = m.group(1)
            // System.out.println("Ram : " + value);
        }
        reader.close()
        totRam = value.toDouble()
        // totRam = totRam / 1024;
        val mb = totRam / 1024.0
        val gb = totRam / 1048576.0
        val tb = totRam / 1073741824.0

        ramGB = gb
        lastValue = if (tb > 1) {
            twoDecimalForm.format(tb).plus(" TB")
        } else if (gb > 1) {
            twoDecimalForm.format(gb).plus(" GB")
        } else if (mb > 1) {
            twoDecimalForm.format(mb).plus(" MB")
        } else {
            twoDecimalForm.format(totRam).plus(" KB")
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        // Streams.close(reader);
    }
    return ramGB
}

fun Context.shareApp() {
    try {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody =
            "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID.trim { it <= ' ' }
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share to"))
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }

}


fun Context.support() {
    val emailIntent = Intent(Intent.ACTION_SEND)
    emailIntent.type = "message/rfc822"
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mobile.hoangtien.ios@gmail.com"))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
    try {
        val installed = checkAppInstall("com.google.android.gm")
        if (installed) {
            emailIntent.setPackage("com.google.android.gm")
            startActivity(emailIntent)
        } else {
            Toast.makeText(
                applicationContext,
                "You not installed Gmail", Toast.LENGTH_LONG
            ).show()
        }
    } catch (e: Exception) {
    }

}

fun Context.checkAppInstall(uri: String): Boolean {
    val pm = packageManager
    try {
        pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return false
}

fun Context.writeReview() {
    val applicationNameId: String = BuildConfig.APPLICATION_ID
    val uri: Uri = Uri.parse("market://details?id=$applicationNameId")
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    goToMarket.addFlags(
        Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
    )

    try {
        startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=$applicationNameId")
            )
        )
    }
}

fun Context.openFacebookPage(pageId: String) {
    val pageUrl = "https://www.facebook.com/$pageId"
    try {
        val applicationInfo: ApplicationInfo =
            getPackageManager().getApplicationInfo("com.facebook.katana", 0)
        if (applicationInfo.enabled) {
            val versionCode: Int =
                getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode
            val url: String
            url = if (versionCode >= 3002850) {
                "fb://facewebmodal/f?href=$pageUrl"
            } else {
                "fb://page/$pageId"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } else {
            throw java.lang.Exception("Facebook is disabled")
        }
    } catch (e: java.lang.Exception) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
    }
}

fun Context.saveImage(context: Context, bitmap: Bitmap): Boolean {

    val storePath: String = Environment.getExternalStorageDirectory()
        .getAbsolutePath() + File.separator.toString() + "dearxy"
    val appDir = File(storePath)
    if (!appDir.exists()) {
        appDir.mkdir()
    }
    val fileName = System.currentTimeMillis().toString() + ".jpg"
    val file = File(appDir, fileName)
    try {
        val fos = FileOutputStream(file)

        val isSuccess: Boolean = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos)
        fos.flush()
        fos.close()

        val uri = Uri.fromFile(file)
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
        return isSuccess
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return false
}
fun Activity.resetActivity() {
    val intent = this.intent
    this.overridePendingTransition(0, 0)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    this.finish()
    this.overridePendingTransition(0, 0)
    this.startActivity(intent)
}
