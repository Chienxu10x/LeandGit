package com.example.appcc.model

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi

data class Style(val string: String) {


//    @RequiresApi(Build.VERSION_CODES.P)
//    fun getStringWithCustomTypeface(): CharSequence {
//        // Apply the custom typeface to the string and return it
//        val spannableString = android.text.SpannableString(string)
//        spannableString.setSpan(android.text.style.TypefaceSpan(customTypeface), 0, string.length, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        return spannableString
//    }

}