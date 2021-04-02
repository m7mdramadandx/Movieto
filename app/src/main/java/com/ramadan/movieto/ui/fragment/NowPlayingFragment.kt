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
import com.ramadan.movieto.data.api.ApiHelper
import com.ramadan.movieto.ui.adapter.RecyclerViewAdapter
import com.ramadan.movieto.ui.viewmodel.ViewModelFactory
import com.ramadan.movieto.ui.viewmodel.WebServiceViewModel
import com.ramadan.movieto.utils.ResponseStatus
import com.ramadan.movieto.utils.showMessage

class NowPlayingFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(com.ramadan.movieto.data.api.RetrofitBuilder.apiService()))
        ).get(WebServiceViewModel::class.java)
    }
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
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun observeData() {
        viewModel.retrieveNowPlaying("en-US", 1).observe(this, {
            when (it.status) {
                ResponseStatus.LOADING -> progress.visibility = View.VISIBLE
                ResponseStatus.SUCCESS -> {
                    progress.visibility = View.GONE
                    it.data?.let { movie -> recyclerViewAdapter.setTopRatedDataList(movie.results) }
                        ?: showMessage(requireContext(), it.message!!)
                }
                ResponseStatus.ERROR -> {
                    progress.visibility = View.GONE
                    showMessage(requireContext(), it.message!!)
                }

            }
        })
    }
}