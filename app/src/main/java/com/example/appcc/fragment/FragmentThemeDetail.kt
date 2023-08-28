package com.example.appcc.fragment

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.DialogCommentBinding
import com.example.appcc.databinding.FragmentThemeDetailBinding
import com.example.appcc.dialog.ThemeDetailDialog
import com.example.appcc.extension.navigateTo
import com.example.appcc.extension.toAssetPath
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX
import com.example.appcc.model.UserModel
import com.example.appcc.utils.Const
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class FragmentThemeDetail : BaseFragment() {
    private lateinit var binding: FragmentThemeDetailBinding

    private val authViewModel: AuthViewModel by viewModels()
    val arg: ThemesFragmentArgs by navArgs()
    private var avarta:String=""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindView() {
        val user=Firebase.auth.currentUser
        val referen=FirebaseDatabase.getInstance().getReference(Const.USER).child(user?.uid.toString()).child("avarta")
        Log.d("TAG", "bindView: "+user?.uid.toString())
        referen.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val avatarData = snapshot.getValue(String::class.java)
                    avarta=avatarData.toString()

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onDataChange: "+error)
                }

            })
        binding.loading.visibility=View.GONE
        val item:ContentX = arg.contentx

//        Log.d("TAGV", "bindView: " + item)
        Glide.with(this).load(Uri.parse(item.previews[0].toAssetPath()))
            .into(binding.imageThemeItem)
        binding.apply {
            btnCustom.setOnClickListener {
                val dialogTheme = ThemeDetailDialog()
                dialogTheme.isCancelable = false
                dialogTheme.show(childFragmentManager, DIALOG_THEME)
            }
            btnGetTheme.setOnClickListener {
//            toGetTheme(item)
                activity?.let { act ->
                    val fragmentGetTheme: FragmentGetTheme = FragmentGetTheme(item).setUpView()
                    (act as MainActivity).replaceFragment(fragmentGetTheme)
                }
            }
            imgshare.setOnClickListener {
                if (user==null){
                    val toast = Toast(requireActivity())
                    toast.duration = Toast.LENGTH_SHORT

                    val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val layout = inflater.inflate(R.layout.custom_toast, null)

// Customize the content of the Toast layout here if needed
                    val toastText = layout.findViewById<TextView>(R.id.toast_text)
                    toastText.text = "Please login"

                    toast.view = layout
                    toast.setGravity(Gravity.CENTER, 0, 0) // Set gravity to center

                    toast.show()
                    return@setOnClickListener
                }
                val dialog:Dialog= Dialog(requireActivity())
                val bindingdialog=DialogCommentBinding.inflate(layoutInflater)
                dialog.setContentView(bindingdialog.root)
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                val window=dialog.window
                window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
                Glide.with(requireActivity()).load(Uri.parse(item.previews[0].toAssetPath()))
                    .into(bindingdialog.imgComment)
                Glide.with(requireActivity()).load(Uri.parse(item.previews[1].toAssetPath()))
                    .into(bindingdialog.imgComment1)
                bindingdialog.btnPost.setOnClickListener {
                    val key: String? =FirebaseDatabase.getInstance().getReference(Const.COMMENT).push().key
                    val commentModel=CommentModel(key.toString(),user.uid,avarta, user.displayName.toString(),bindingdialog.edtComment.text.toString(),item)

                    authViewModel.addTimeline(commentModel,key.toString())
                    authViewModel.addTimeline.observe(viewLifecycleOwner) { state ->
                        when(state){
                            is UiState.Loading -> {
                            binding.loading.visibility=View.VISIBLE
//                            binding.button.text = ""
                            }
                            is UiState.Failure -> {
//                            binding.btnProgressAr.hide()
//                            binding.button.text = "Create"
                                Toast.makeText(requireActivity(),state.error,Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Success -> {
                                binding.loading.visibility=View.GONE
                                dialog.dismiss()
                                Toast.makeText(requireActivity(),state.data,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                dialog.show()

            }
            flFavorite.setOnClickListener {
                if (user==null){
                    val toast = Toast(requireActivity())
                    toast.duration = Toast.LENGTH_SHORT

                    val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val layout = inflater.inflate(R.layout.custom_toast, null)

// Customize the content of the Toast layout here if needed
                    val toastText = layout.findViewById<TextView>(R.id.toast_text)
                    toastText.text = "Please login"

                    toast.view = layout
                    toast.setGravity(Gravity.CENTER, 0, 0) // Set gravity to center

                    toast.show()
                    return@setOnClickListener
                }
                authViewModel.addFavorite(item,item.title)
                authViewModel.addFavorite.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{
                            binding.loading.visibility=View.VISIBLE
                        }
                        is UiState.Failure ->{

                        }
                        is UiState.Success ->{
                            binding.loading.visibility=View.GONE
                            Toast.makeText(requireActivity(),state.data,Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
            }

        }

    }

    override fun observeData() {

    }
//    override fun baseBackPressed() {
//        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Log.d("aaaa", "handleOnBackPressed: aaaa")
//                    onBackPressed()
//                }
//            })
//
//    }
//    private fun onBackPressed() {
//        activity?.let { act ->
//            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            showInBaseNavigationView()
//            showIntoolBar()
//
////            (act as MainActivity).removeFragment(this)
//        }
//    }
    fun onSetupView(): FragmentThemeDetail {
        return FragmentThemeDetail()

    }

    fun toGetTheme(){

    }

    companion object {
        private const val DIALOG_THEME = "DIALOG_THEME"
    }
    fun checkuser(str:String){
        val user=Firebase.auth.currentUser
        if (user==null){
            val toast = Toast(requireActivity())
            toast.duration = Toast.LENGTH_SHORT

            val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, null)

// Customize the content of the Toast layout here if needed
            val toastText = layout.findViewById<TextView>(R.id.toast_text)
            toastText.text = str

            toast.view = layout
            toast.setGravity(Gravity.CENTER, 0, 0) // Set gravity to center

            toast.show()
            return
        }
    }
}