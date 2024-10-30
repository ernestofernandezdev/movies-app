package com.example.moviesapp.ddl.data.dto

import com.google.gson.annotations.SerializedName

class MovieResultDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDTO>
)