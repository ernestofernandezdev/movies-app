package com.example.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.moviesapp.databinding.MovieItemLayoutBinding
import com.example.moviesapp.models.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.show(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    inner class MovieViewHolder(
        private val binding: MovieItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun show(movie: Movie) {
            binding.movieItemTitle.text = movie.originalTitle
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/original${movie.image}")
                .into(binding.movieItemImage)

            println(movie.image)

            binding.movieItemTitle.setOnClickListener {
                onMovieClick(movie)
            }
        }

    }
}