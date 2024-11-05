package com.example.moviesapp.ddl.data.dto

import com.google.gson.annotations.SerializedName

class GenreDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String)