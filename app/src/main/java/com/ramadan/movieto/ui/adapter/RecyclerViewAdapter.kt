package com.ramadan.movieto.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.movieto.R
import com.ramadan.movieto.data.model.Result
import com.ramadan.movieto.ui.activity.MovieDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card.view.*


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.CustomView>() {
    private var isDashboard = true
    private var topRatedList: MutableList<Result> = mutableListOf()
    private var nowPlayingList: MutableList<Result> = mutableListOf()

    fun setTopRatedDataList(data: MutableList<Result>) {
        topRatedList = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val view: View = when {
            topRatedList.size > 0 -> {
                LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
            }
            nowPlayingList.size > 0 -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        }
        return CustomView(view)
    }


    override fun onBindViewHolder(holder: CustomView, position: Int) {
        when {
            topRatedList.isNotEmpty() -> holder.topRatedView(topRatedList[position])
            nowPlayingList.isNotEmpty() -> holder.topRatedView(nowPlayingList[position])
        }
    }

    override fun getItemCount(): Int {
        return when {
            topRatedList.isNotEmpty() -> topRatedList.size
            nowPlayingList.isNotEmpty() -> nowPlayingList.size
            else -> 0
        }
    }

    inner class CustomView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ctx: Context = itemView.context

        fun topRatedView(movie: Result) {
            itemView.apply {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                    .error(R.drawable.ic_error_img)
                    .placeholder(R.drawable.load_img)
                    .into(cardImage)
                cardTitle.text = movie.original_title
                cardVote.text = movie.vote_average.toString()
                setOnClickListener {
                    Intent(ctx, MovieDetailsActivity::class.java).apply {
                        putExtra("movieObj", movie)
                        ctx.startActivity(this)
                    }
                }
            }
        }

    }
}