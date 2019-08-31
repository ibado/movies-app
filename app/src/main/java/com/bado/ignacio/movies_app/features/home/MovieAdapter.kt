package com.bado.ignacio.movies_app.features.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bado.ignacio.movies_app.R
import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.features.ImageLoader

class MovieAdapter(private val dataSet: MutableList<Movie>, private val imageLoader: ImageLoader) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item, viewGroup, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    fun addItems(movies: List<Movie>) {
        dataSet.addAll(movies)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image by lazy { itemView.findViewById<ImageView>(R.id.image_movie_item) }
        private val title by lazy { itemView.findViewById<TextView>(R.id.title_movie_item) }

        fun bind(item: Movie) {
            title.text = item.title
            item.posterPath?.let { imageLoader.loadImage(it, image) }
        }

    }

}
