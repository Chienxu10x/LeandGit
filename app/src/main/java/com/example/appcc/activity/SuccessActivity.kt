package com.example.appcc.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcc.base.BaseActivity
import com.example.appcc.databinding.ActivitySuccessBinding
import com.example.appcc.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class SuccessActivity : BaseActivity() {

    private lateinit var bind: ActivitySuccessBinding
    override fun bindView() {
        val bitmap = intent.getParcelableExtra<Bitmap>(Utils.INTENT_BITMAP)
        val name = intent.getStringExtra(Utils.INTENT_LABEL)
        if(name != null){
            bind.tvAppName.text = name
        }
        if(bitmap != null){
            bind.ivImage1.setImageBitmap(bitmap)
            bind.ivIamge2.setImageBitmap(bitmap)
        }

        bind.btnBack.setOnClickListener {
            onBackPressed()
        }

        bind.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun observeData() {

    }

    fun onBottomViewProvides(): BottomNavigationView? {
        return null
    }

}