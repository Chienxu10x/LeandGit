package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.AdapterWallPaper
import com.example.appcc.adapter.AdapterWallPaperDown
import com.example.appcc.adapter.AdapterWallPaperFavorite
import com.example.appcc.adapter.FavoriteAdapter
import com.example.appcc.adapter.RecyclerAdapterTheme2
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentSetWallPaperBinding
import com.example.appcc.databinding.FragmentWallpaperBinding
import com.example.appcc.model.ContentX
import com.example.appcc.viewmodel.IconViewModel

class WallpaperFragment : BaseFragment(),
    AdapterWallPaper.ClickListener,
    AdapterWallPaperDown.ClickListener,
    AdapterWallPaperFavorite.ClickListener

{

    private lateinit var binding: FragmentWallpaperBinding
    private val iconViewModel : IconViewModel by activityViewModels()
    private lateinit var adapterWallPager: AdapterWallPaper
    private lateinit var adapterWallPagerDown: AdapterWallPaperDown
    private lateinit var adapterWallPaperFavorite: AdapterWallPaperFavorite


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWallpaperBinding.inflate(layoutInflater)
        return binding.root

    }
    override fun bindView() {
        binding.btnSeeAllTopDown.setOnClickListener{
            activity?.let {act->
                var fragmentWallPaperSeeAll: FragmentWallPaperSeeAll = FragmentWallPaperSeeAll()
                (act as MainActivity).replaceFragment(fragmentWallPaperSeeAll)
            }
        }

        binding.btnFavorite.setOnClickListener{
            activity?.let {act->
                var fragmentWallPaperSeeAll2: FragmentWallPaperSeeAll2 = FragmentWallPaperSeeAll2()
                (act as MainActivity).replaceFragment(fragmentWallPaperSeeAll2)
            }

        }

    }

    override fun observeData() {
        iconViewModel.currentTheme.observe(this) {
            val originalItemList = listOf<ContentX>(
                it.content[9],
                it.content[0],
                it.content[1],
                it.content[2],
                it.content[3],
                it.content[4],
                it.content[5],
                it.content[6],
                it.content[7],
                it.content[8],
            )
            adapterWallPager = AdapterWallPaper(originalItemList, this)
//            adapterWallPager = RecyclerAdapterTheme2(originalItemList)
            binding.recyclerTraning.adapter = adapterWallPager

            val listDown = listOf<ContentX>(
                it.content[7],
                it.content[0],
                it.content[1],
                it.content[2],
                it.content[8],
                it.content[9],
            )
            adapterWallPagerDown = AdapterWallPaperDown(listDown, this)
//            adapterWallPager = RecyclerAdapterTheme2(originalItemList)
            binding.recyclerTopDown.adapter = adapterWallPagerDown

            val listFavorite = listOf<ContentX>(
                it.content[2],
                it.content[9],
                it.content[6],
                it.content[7],
                it.content[0],
            )
            adapterWallPaperFavorite = AdapterWallPaperFavorite(listFavorite, this)
//            adapterWallPager = RecyclerAdapterTheme2(originalItemList)
            binding.recyclerWallpaperFavorite.adapter = adapterWallPaperFavorite
        }

//
//
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

    override fun onClickListener(item: ContentX) {
        activity?.let {act->
            activity?.let {act->
                var fragmentSetWallPaper2: FragmentSetWallPaper2 = FragmentSetWallPaper2(item)
                (act as MainActivity).replaceFragment(fragmentSetWallPaper2)
            }
        }
    }

}