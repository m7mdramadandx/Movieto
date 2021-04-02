package com.ramadan.movieto.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import com.ramadan.movieto.MainActivity
import com.ramadan.movieto.R
import com.ramadan.movieto.data.api.ApiHelper
import com.ramadan.movieto.data.api.RetrofitBuilder
import com.ramadan.movieto.ui.adapter.RecyclerViewAdapter
import com.ramadan.movieto.ui.viewmodel.ViewModelFactory
import com.ramadan.movieto.ui.viewmodel.WebServiceViewModel
import com.ramadan.movieto.utils.ResponseStatus
import com.ramadan.movieto.utils.showMessage


class SearchFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService()))
        ).get(WebServiceViewModel::class.java)
    }
    private lateinit var toolbar: Toolbar
    private lateinit var progress: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var searchBar: MaterialSearchBar
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        recyclerViewAdapter = RecyclerViewAdapter()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onDetach() {
        super.onDetach()
//        (activity as MainActivity).supportActionBar?.show()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
//        searchBar = root.findViewById(R.id.searchBar)
        progress = root.findViewById(R.id.progress)
        recyclerView = root.findViewById(R.id.recycler)
//        toolbar = root.findViewById(R.id.toolbar)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerViewAdapter
//        searchBar.setOnSearchActionListener(this)
//        searchBar.setSuggstionsClickListener(this)
//        searchBar.setSpeechMode(true)


    }

    private fun searchMovie(query: String) {
        viewModel.searchMovie(query).observe(this, {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.appSearchBar)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView
        searchView?.let {
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    recyclerViewAdapter.notifyDataSetChanged()
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    searchMovie(query)
                    return true
                }
            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        menu.clear()
//        inflater.inflate(R.menu.search_menu, menu)
//        val searchView =
//            MenuItemCompat.getActionView(menu.findItem(R.id.appSearchBar)) as SearchView
////        val searchView =
////            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
////        menu.findItem(R.id.appSearchBar).apply { actionView = searchView }
//        searchView.queryHint = "Movie name.."
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                searchMovie(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                recyclerViewAdapter.notifyDataSetChanged()
//                return false
//            }
//        })
//
//        return super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun OnItemClickListener(position: Int, v: View?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun OnItemDeleteListener(position: Int, v: View?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onSearchStateChanged(enabled: Boolean) {
//        val s = if (enabled) "enabled" else "disabled"
//        showMessage(requireContext(), "Search $s")
//    }
//
//    override fun onSearchConfirmed(text: CharSequence?) {
//        searchMovie(text.toString())
//    }
//
//    override fun onButtonClicked(buttonCode: Int) {
//        when (buttonCode) {
////            MaterialSearchBar.BUTTON_NAVIGATION -> drawer.openDrawer(Gravity.LEFT)
////            MaterialSearchBar.BUTTON_SPEECH -> openVoiceRecognizer()
//        }
//    }


}