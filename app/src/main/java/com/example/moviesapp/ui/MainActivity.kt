package com.example.moviesapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getMoviesBtn.setOnClickListener{
            moviesViewModel.getMovies()
        }

        moviesViewModel.loading.onEach { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.notFoundMessage.visibility = View.INVISIBLE
                binding.movieList.visibility = View.INVISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                if (moviesViewModel.gotMovies.value) {
                    binding.movieList.visibility = View.VISIBLE
                    binding.notFoundMessage.visibility = View.INVISIBLE
                } else {
                    binding.movieList.visibility = View.INVISIBLE
                    binding.notFoundMessage.visibility = View.VISIBLE
                }
            }
        }.launchIn(lifecycleScope)


    }
}