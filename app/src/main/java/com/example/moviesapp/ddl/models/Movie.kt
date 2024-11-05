package com.example.moviesapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    val originalTitle: String?,
    val image: String?,
    val overview: String?,
    val genres: List<Int>?,
    val rating: Double,
    val voteCount: Int): Parcelable {
}