package com.example.appcc.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun View.layouInflater(): LayoutInflater = LayoutInflater.from(this.context)
fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibble() {
    this.visibility = View.VISIBLE
}

fun TextView.string() = text.toString()

suspend fun Context.dowloadImage(url: String): Bitmap? = suspendCoroutine {
    try {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    it.resume(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    it.resumeWithException(Throwable("Image Cleared"))
                }

            })
    } catch (e: Exception) {

    }
}

fun Int.percent(percent: Int): Int {
    return (this * percent) / 100
}

fun ViewGroup.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(this.context).inflate(view, this, false)
}

fun View.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(this.context).inflate(view, null, false)

}

fun Context.getLinearVerticalLayoutManager(
    reverseLayout: Boolean = false
): LinearLayoutManager {
    return LinearLayoutManager(this, LinearLayoutManager.VERTICAL, reverseLayout)
}

fun Context.getLinearHorizontalLayoutManager(
    reverseLayout: Boolean = false
): LinearLayoutManager {
    return LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, reverseLayout)
}

fun Context.getGirdLayoutManager(
    spanCount: Int = 3
): GridLayoutManager {
    return GridLayoutManager(this, spanCount)
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun Context.getDisplayMetrics(): DisplayMetrics {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    return DisplayMetrics().apply {
        windowManager.defaultDisplay.getMetrics(this)
    }
}

fun Context.getDisplayWidth() = getDisplayMetrics().widthPixels

fun Context.getDisplayHeight() = getDisplayMetrics().heightPixels

fun View.setScaleWithDisplay(percent: Int){
    val scaleWith = context.getDisplayWidth().percent(percent)
    layoutParams.width = scaleWith
}

fun View.setScaleHeightDisplay(percent : Int){
    val scaledHeight = context.getDisplayHeight().percent(percent)
    layoutParams.height = scaledHeight
}

fun View.setScaleDisplay(percent: Int){
    setScaleHeightDisplay(percent)
    setScaleWithDisplay(percent)
}




