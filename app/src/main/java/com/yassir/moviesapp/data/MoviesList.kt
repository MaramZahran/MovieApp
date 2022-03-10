package com.yassir.moviesapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesList(
    val page: Int?,
    @Json(name = "total_pages") val pageCount: Int?,
    @Json(name = "total_results") val totalResults: Int?,
    val results: List<Movie>?
)
