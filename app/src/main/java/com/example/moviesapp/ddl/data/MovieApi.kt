package com.example.moviesapp.ddl.data

import com.example.moviesapp.ddl.data.dto.GenresResultDTO
import com.example.moviesapp.ddl.data.dto.MovieResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getMovies(): Response<MovieResultDTO>

    @GET("genre/movie/list?language=en")
    suspend fun getGenres(): Response<GenresResultDTO>

    @GET("search/movie?include_adult=false&language=en-US&page=1")
    suspend fun getMoviesByName(
        @Query("query") title: String
    ): Response<MovieResultDTO>

}