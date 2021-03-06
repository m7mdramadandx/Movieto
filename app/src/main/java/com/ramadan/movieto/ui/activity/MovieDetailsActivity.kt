package com.ramadan.movieto.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ramadan.movieto.R
import com.ramadan.movieto.data.model.MovieTable
import com.ramadan.movieto.ui.viewmodel.MovieViewModel
import com.ramadan.movieto.utils.genresNamesList
import com.ramadan.movieto.utils.snackBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
    private var movieTable: MovieTable? = null
    private var isFavorite = false
    private var genres = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.getSerializableExtra("movieObj")?.let { movieTable = it as MovieTable }
        loadData()
    }


    @Suppress("DEPRECATION")
    private fun loadData() {
        movieTable?.let {
            toolbar_layout.setContentScrimColor(resources.getColor(R.color.colorPrimary))
            toolbar_layout.setCollapsedTitleTextColor(Color.WHITE)
            toolbar_layout.setExpandedTitleColor(Color.WHITE)
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .placeholder(R.drawable.load_img)
                .error(R.drawable.ic_error_img)
                .into(coverImg)
            toolbar.title = it.title
            setSupportActionBar(toolbar)
            movieDescription.text = it.overview
            movieReleaseDate.text = it.release_date
            movieOriginalLanguage.text = it.original_language
            "${(it.vote_average)} from ${it.vote_count} users ".also {
                movieVoteAverage.text = it
            }
            if (it.is_favorite) {
                addToFavorite.setImageResource(R.drawable.ic_favorite)
                isFavorite = it.is_favorite
            }
            it.genre_ids.forEach { genres += genresNamesList.getValue(it) + " " }
            movieVoteGenres.text = genres
        }
    }

    fun addToFavorite(view: View) {
        if (!isFavorite) {
            isFavorite = !isFavorite
            viewModel.addMovie(this, movieTable)
            addToFavorite.setImageResource(R.drawable.ic_favorite)
            view.snackBar("Added to favorite")
        } else {
            isFavorite = !isFavorite
            movieTable?.let { it1 -> viewModel.removeMovie(this, it1) }
            addToFavorite.setImageResource(R.drawable.ic_favorite_grey)
            view.snackBar("Removed from favorite")
        }
    }
}