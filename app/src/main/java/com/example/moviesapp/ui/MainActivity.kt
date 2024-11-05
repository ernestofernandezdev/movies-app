package com.example.moviesapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.MovieAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPopularMovies()

        binding.getMoviesBtn.setOnClickListener{
            mainViewModel.setSearchBoxText(binding.searchBox.text.toString())
            mainViewModel.getMoviesByTitle()
        }

        mainViewModel.loading.onEach {loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.movieList.visibility = View.INVISIBLE
                binding.notFoundMessage.visibility = View.INVISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.notFoundMessage.visibility = View.VISIBLE
            }
        }.launchIn(lifecycleScope)

        mainViewModel.gotMovies.onEach { gotMovies ->
            if (gotMovies) {
                binding.movieList.visibility = View.VISIBLE
                binding.movieList.adapter = MovieAdapter(
                    movies = mainViewModel.movies.value ?: emptyList(),
                    onMovieClick = {movie ->
                        val intent = Intent(this, MovieActivity::class.java)

                        intent.putExtra("movie", movie)

                        startActivity(intent)
                    }
                )
                binding.notFoundMessage.visibility = View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

    }
}