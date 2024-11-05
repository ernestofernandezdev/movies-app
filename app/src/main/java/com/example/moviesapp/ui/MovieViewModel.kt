package com.example.moviesapp.ui

import androidx.lifecycle.ViewModel
import kotlin.streams.toList

import androidx.lifecycle.viewModelScope
import com.example.moviesapp.ddl.data.MovieRepository
import com.example.moviesapp.ddl.data.dto.GenreDTO
import com.example.moviesapp.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie = _movie.asStateFlow()

    private val _genres = MutableStateFlow<List<GenreDTO>?>(null)
    val genres = _genres.asStateFlow()

    private val _gotGenres = MutableStateFlow<Boolean>(false)
    val gotGenres = _gotGenres.asStateFlow()

    private val _genresText = MutableStateFlow<String>("")
    val genresText = _genresText.asStateFlow()

    fun setMovie(movie: Movie) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            _movie.value = movie

            _genres.value = movieRepository.getGenres()?.genres

            var genresTextStr = ""
            for (genre in _genres.value!!) {
                println("" + genre.id + " " + genre.name)
                if (_movie.value?.genres?.contains(genre.id) == true) {
                    genresTextStr += genre.name + " "
                }
            }

            _genresText.value = genresTextStr
            _gotGenres.value = true
        }
    }

}