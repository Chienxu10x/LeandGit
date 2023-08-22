package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.AdapterWallPaperDown
import com.example.appcc.adapter.RecyclerAdapterTheme2
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentFragmetSeachThemeBinding
import com.example.appcc.databinding.FragmentWallPaperSeeAllBinding
import com.example.appcc.model.ContentX
import com.example.appcc.viewmodel.IconViewModel

class FragmentWallPaperSeeAll : BaseFragment(), AdapterWallPaperDown.ClickListener {
    private lateinit var binding: FragmentWallPaperSeeAllBinding
    private val iconViewModel : IconViewModel by activityViewModels()
    private lateinit var adapterWallPaperDown : AdapterWallPaperDown

    override fun bindView() {
        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }

    override fun observeData() {
        iconViewModel.currentTheme.observe(this){

            val originalItemList = listOf<ContentX>(
                it.content[7],
                it.content[0],
                it.content[1],
                it.content[2],
                it.content[8],
                it.content[9],
                it.content[3],
                it.content[4],
                it.content[5],
                it.content[6],
            )
            adapterWallPaperDown = AdapterWallPaperDown(originalItemList, this)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerSeeDetail.layoutManager = gridLayoutManager
            binding.recyclerSeeDetail.adapter = adapterWallPaperDown


        }



        iconViewModel.allTheme.observe(this){
            Log.d("TAG", "observeData: "+"a1")
            iconViewModel.getThemeByFilter(0)
            binding.tabMenu.removeAllTabs()
            it.contents.forEach{
                val tab = binding.tabMenu.newTab()
                tab.text = it.title
//                Log.d("TAG", "observeData: "+it.content.get(0).previews)
                binding.tabMenu.addTab(tab)
            }
        }
        iconViewModel.loadAllResource(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallPaperSeeAllBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClickListener(item: ContentX) {
        activity?.let {act->
            var fragmentSetWallPaper2: FragmentSetWallPaper2 = FragmentSetWallPaper2(item)
            (act as MainActivity).replaceFragment(fragmentSetWallPaper2)
        }
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)
        }
    }

}