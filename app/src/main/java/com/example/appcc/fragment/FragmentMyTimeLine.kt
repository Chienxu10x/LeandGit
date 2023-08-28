package com.example.appcc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.RecyclerAdapterTimeline
import com.example.appcc.databinding.FragmentMyTimeLineBinding
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMyTimeLine : Fragment() {
    private lateinit var binding: FragmentMyTimeLineBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyTimeLineBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
        val user= Firebase.auth.currentUser
        if (user==null){
            binding.loading.visibility=View.GONE
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
            return
        }
        authViewModel.geMytTimeLine()
        binding.recyclerviewTimeline.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapterTimeline(requireActivity())
        binding.recyclerviewTimeline.adapter = adapter
        authViewModel.mytimeline.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.d("TAG",
                        "onViewCreated: " +"loading"
                    )
                    binding.loading.visibility=View.VISIBLE
                }

                is UiState.Failure -> {
                    Log.d("TAG",
                        "onViewCreated: " +"loading"+state.error
                    )
//                    binding.progressBar.hide()
//                    toast(state.error)
                    Toast.makeText(requireActivity(), state.error, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
//                    binding.progressBar.hide()
                    binding.loading.visibility=View.GONE
                    adapter.updateList(state.data.toMutableList())
                    Log.d("TAG",
                        "onViewCreated: " + state.data.toMutableList().size
                    )
//                    adapter.updateList(state.data.toMutableList())
                }
            }
        }
    }
    fun onSetupView():FragmentMyTimeLine{
        return FragmentMyTimeLine()
    }
    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
    companion object {

    }
}