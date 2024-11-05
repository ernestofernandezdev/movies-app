package com.example.moviesapp.ddl.data

import com.example.moviesapp.ddl.data.dto.GenresResultDTO
import com.example.moviesapp.ddl.data.dto.MovieResultDTO
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
) {
    suspend fun getMovies(): MovieResultDTO? {
        val movies = movieDataSource.getMovies()
        return movies
    }

    suspend fun getGenres(): GenresResultDTO? {
        val genres = movieDataSource.getGenres()
        return genres
    }

    suspend fun getMoviesByTitle(title: String): MovieResultDTO? {
        val movies = movieDataSource.getMoviesByName(title)
        return movies
    }
}