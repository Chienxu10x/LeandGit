package com.example.appcc.extension

import com.example.appcc.utils.Const

fun String.toAssetPath() : String {
    return if(this.contains(Const.ASSET_PATH)){
        this
    } else{
        Const.ASSET_PATH.plus(this)
    }
}