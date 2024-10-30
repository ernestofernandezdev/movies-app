package com.example.moviesapp.ddl.data.dto

import com.example.moviesapp.models.Movie
import com.google.gson.annotations.SerializedName

class MovieDTO(
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("genre_ids")
    val genres: List<Int>,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovieEntity(): Movie {
        return Movie(
            originalTitle,
            image,
            overview,
            genres,
            rating,
            voteCount
        )
    }
}