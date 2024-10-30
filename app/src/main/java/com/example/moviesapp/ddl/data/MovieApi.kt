package com.example.moviesapp.ddl.data

import com.example.moviesapp.ddl.data.dto.MovieResultDTO
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getMovies(): Response<MovieResultDTO>

}