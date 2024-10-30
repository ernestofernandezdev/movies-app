package com.example.moviesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.ddl.data.MovieRepository
import com.example.moviesapp.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MoviesViewModel @Inject constructor (
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>?>(null)
    val movies = _movies.asStateFlow()

    private val _gotMovies = MutableStateFlow<Boolean>(false)
    val gotMovies = _gotMovies.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading.asStateFlow()

    fun getMovies() {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            _loading.value = true
            _gotMovies.value = false
            _movies.value = null

            val movies = movieRepository.getMovies()

            _loading.value = false
            if (movies != null) {
                _movies.value = movies.results.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }
            _gotMovies.value = movies != null
        }
    }
}