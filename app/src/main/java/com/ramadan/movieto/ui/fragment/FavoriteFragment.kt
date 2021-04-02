package com.ramadan.movieto.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.movieto.MainActivity
import com.ramadan.movieto.R
import com.ramadan.movieto.ui.adapter.RecyclerViewAdapter

class FavoriteFragment : Fragment() {
    private lateinit var progress: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        recyclerViewAdapter = RecyclerViewAdapter()
    }

    override fun onResume() {
        super.onResume()
//        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        return root
    }

}