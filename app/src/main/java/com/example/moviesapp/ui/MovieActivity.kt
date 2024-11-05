package com.example.moviesapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ActivityMovieBinding
import com.example.moviesapp.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>("movie")
        println("movie not null")
        if (movie != null) {
            movieViewModel.setMovie(movie)
            binding.notFoundMessage.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }

        movieViewModel.gotGenres.onEach { gotGenres ->
            if (gotGenres) {
                binding.progressBar.visibility = View.INVISIBLE

                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/original${movie?.image}")
                    .into(binding.movieImage)

                binding.movieTitle.text = movie?.originalTitle
                binding.movieGenres.text = movieViewModel.genresText.value
                binding.rating.text = movie?.rating.toString()
                binding.votes.text = movie?.voteCount.toString()
                binding.movieRating.visibility = View.VISIBLE
                binding.movieResume.text = movie?.overview
            }
        }.launchIn(lifecycleScope)

    }
}