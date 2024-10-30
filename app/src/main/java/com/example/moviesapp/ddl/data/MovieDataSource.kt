package com.example.moviesapp.ddl.data

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
                println(response.message())
                return@withContext response.body()
            }catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
                return@withContext null
            }
        }
    }

}