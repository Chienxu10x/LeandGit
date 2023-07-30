package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R
import com.example.appcc.adapter.OnItemClickListener
import com.example.appcc.adapter.OnTabNameChangedListener
import com.example.appcc.adapter.RecyclerAdapterTheme



class FragmetThemeChild : Fragment(), OnItemClickListener {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragmet_theme_child, container, false)
        setupRecyclerView(view)
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            val list : List<String> = listOf(
//                "Chuỗi 1",
//                "Chuỗi 1",
//                )
//
//            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_theme)
//
//            val adapter = RecyclerAdapterTheme(list)
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//    }

    private fun setupRecyclerView(view: View) {
        val list : List<String> = listOf(
            "Chuỗi 1",
            "Chuỗi 1",
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_theme)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter = RecyclerAdapterTheme(list, this)
        recyclerView.adapter = adapter
    }

    // Xử lý sự kiện nhấp vào mục trong Fragment cha
    override fun onItemClick(position: Int) {
        // Tạo Fragment con mới
        val childFragment = FragmentThemeDetail()

        // Lấy FragmentManager của Fragment cha và chuyển sang Fragment con
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_theme, childFragment)
        transaction.addToBackStack(null) // Thêm vào back stack để quay lại Fragment cha khi nhấn nút Back
        transaction.commit()
    }


}