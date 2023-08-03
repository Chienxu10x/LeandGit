package com.example.appcc.extension

import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.BRAND
    return if (model.startsWith(manufacturer)) {
        capitalize(model)
    } else capitalize(manufacturer) + " " + model
}

private fun capitalize(str: String): String {
    if (TextUtils.isEmpty(str)) {
        return str
    }
    val arr = str.toCharArray()
    var capitalizeNext = true
    val phrase = StringBuilder()
    for (c in arr) {
        if (capitalizeNext && Character.isLetter(c)) {
            phrase.append(Character.toUpperCase(c))
            capitalizeNext = false
            continue
        } else if (Character.isWhitespace(c)) {
            capitalizeNext = true
        }
        phrase.append(c)
    }
    return phrase.toString()
}

fun isPackageInstalled(packageName: String?, packageManager: PackageManager): Boolean {
    return try {
        packageManager.getApplicationInfo(packageName!!, 0).enabled
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}
