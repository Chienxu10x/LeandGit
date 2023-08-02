package com.example.appcc.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OpenAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val res = openApp(this, packageName)
        if (!res) {
            Toast.makeText(this, "App isn't installed", Toast.LENGTH_SHORT).show()
        }
        finish()


    }

    fun openApp(context: Context, packageName: String?): Boolean {
        val manager = context.packageManager
        return try {
            val i = manager.getLaunchIntentForPackage(packageName!!) ?: return false
            i.addCategory(Intent.CATEGORY_LAUNCHER)
            context.startActivity(i)
            true
        } catch (e: Exception) {
            false
        }
    }

}
