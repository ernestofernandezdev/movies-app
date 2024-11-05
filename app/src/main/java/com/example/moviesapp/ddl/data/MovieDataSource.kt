package com.example.moviesapp.ddl.data

import com.example.moviesapp.ddl.data.dto.GenresResultDTO
import com.example.moviesapp.ddl.data.dto.MovieResultDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getMovies(): MovieResultDTO? {
        return withContext(Dispatchers.IO) {
            try {
                val response = movieApi.getMovies()
                return@withContext response.body()
            }catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    suspend fun getGenres(): GenresResultDTO? {
        return withContext(Dispatchers.IO) {
            try {
                val response = movieApi.getGenres()
                return@withContext response.body()
            }catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

    suspend fun getMoviesByName(title: String): MovieResultDTO? {
        return withContext(Dispatchers.IO) {
            try {
                val response = movieApi.getMoviesByName(title)
                return@withContext response.body()
            }catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

}