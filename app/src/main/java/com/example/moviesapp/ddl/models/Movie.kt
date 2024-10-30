package com.example.moviesapp.models

import com.google.gson.annotations.SerializedName

class Movie(
    val originalTitle: String,
    val image: String,
    val overview: String,
    val genres: List<Int>,
    val rating: Double,
    val voteCount: Int) {
}