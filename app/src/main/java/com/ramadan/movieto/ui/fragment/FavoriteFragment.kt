package com.ramadan.movieto.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ramadan.movieto.MainActivity
import com.ramadan.movieto.R
import com.ramadan.movieto.ui.adapter.RecyclerViewAdapter
import com.ramadan.movieto.ui.viewmodel.MovieViewModel

class FavoriteFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
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
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.recycler_view, container, false)
        progress = root.findViewById(R.id.progress)
        recyclerView = root.findViewById(R.id.global_recycler_view)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerViewAdapter
        progress.visibility = View.GONE
    }

    private fun observeData() {
        viewModel.retrieveMovies(requireContext()).observe(this, {
            it?.let(recyclerViewAdapter::setFavoriteDataList)
        })
    }
}