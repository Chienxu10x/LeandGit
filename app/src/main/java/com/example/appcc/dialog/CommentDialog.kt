package com.example.appcc.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.AdapterCommentTheme
import com.example.appcc.adapter.RecyclerAdapterIcon
import com.example.appcc.adapter.RecyclerAdapterTimelineIcon
import com.example.appcc.databinding.FragmentCommentDialogBinding
import com.example.appcc.databinding.FragmentIconDialogBinding
import com.example.appcc.fragment.FragmentIconDetail
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import com.example.appcc.viewmodel.IconsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentDialog : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentCommentDialogBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentDialogBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        isCancelable=true
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.getTimeLine()
        binding.recyclerCommentThemeDetail.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterCommentTheme(requireActivity())
        binding.recyclerCommentThemeDetail.adapter = adapter
        authViewModel.timeline.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
//                    binding.loading.visibility=View.VISIBLE
                }

                is UiState.Failure -> {
                    Toast.makeText(requireActivity(),state.error, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
//                    binding.progressBar.hide()
//                    binding.loading.visibility=View.GONE
                    adapter.updateList(state.data.toMutableList())
                    Log.d("TAG", "onViewCreated: "+state.data.toMutableList())
//                    adapter.updateList(state.data.toMutableList())
                }
            }
        }

    }
}