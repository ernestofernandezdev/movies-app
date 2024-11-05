package com.example.moviesapp.ddl.data.dto

import com.google.gson.annotations.SerializedName

class GenresResultDTO(
    @SerializedName("genres")
    val genres: List<GenreDTO>)