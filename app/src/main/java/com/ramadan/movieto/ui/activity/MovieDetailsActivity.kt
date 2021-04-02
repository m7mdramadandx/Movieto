package com.ramadan.movieto.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ramadan.movieto.R
import com.ramadan.movieto.data.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.getSerializableExtra("movieObj")?.let { result = it as Result }
        loadData()
    }

    private fun loadData() {
        result?.let {
            toolbar_layout.setContentScrimColor(resources.getColor(R.color.colorPrimary))
            toolbar_layout.setCollapsedTitleTextColor(Color.WHITE)
            toolbar_layout.setExpandedTitleColor(Color.WHITE)
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + it.poster_path)
                .placeholder(R.drawable.load_img)
                .error(R.drawable.ic_error_img)
                .into(coverImg)
            toolbar.title = it.title
            setSupportActionBar(toolbar)
            movieDescription.text = it.overview
            "${(it.vote_average * 10)}% from ${it.vote_count} users ".also {
                movieVoteAverage.text = it
            }
            movieVoteGenres.text = it.genre_ids.toString()
        }
    }
}