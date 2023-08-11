package com.example.appcc.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.greenrobot.eventbus.EventBus

fun Fragment.showDialog(dialogFragment: DialogFragment, tag: String, fm: FragmentManager) {
    fm.findFragmentByTag(tag).let { fragment ->
        fragment ?: let {
            fm.beginTransaction().let { transition ->
                dialogFragment.show(transition, tag)
            }
        }
    }
}

fun Fragment.postUIEvent(event: Any) {
    EventBus.getDefault().post(event)
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)

}