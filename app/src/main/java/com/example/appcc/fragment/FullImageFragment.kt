//package com.example.appcc.fragment
//
//import android.os.Bundle
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import com.example.appcc.base.BaseFragment
//import com.example.appcc.databinding.FragmentItemImageBinding
//import com.example.appcc.extension.toAssetPath
//
//class FullImageFragment() : BaseFragment() {
//    private lateinit var binding: FragmentItemImageBinding
//
//    override fun bindView() {
//
//        if(isWallPaper){
//            binding.ivImg.scaleType = ImageView.ScaleType.CENTER_CROP
//        }else{
//            binding.ivImg.scaleType = ImageView.ScaleType
//                .FIT_CENTER
//        }
//
//        Glide.with(this).load(image.toAssetPath()).into(binding.ivImg)
//    }
//
//    override fun observeData() {
//
//    }
//
//    var image = ""
//    var isWallPaper = false
//    override fun setArguments(args: Bundle?) {
//        super.setArguments(args)
//        image = args?.getString("image")!!
//        isWallPaper = args.getBoolean("wallpaper", false)
//    }
//
//
//}