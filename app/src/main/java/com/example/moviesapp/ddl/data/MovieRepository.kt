package com.example.moviesapp.ddl.data

import com.example.moviesapp.ddl.data.dto.MovieResultDTO
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
) {
    suspend fun getMovies(): MovieResultDTO? {
        val movies = movieDataSource.getMovies()
        println(movies)
        return movies
    }
}