package com.yassir.moviesapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,
    @Json(name = "poster_path") val posterPath: String?,
    val adult: Boolean?,
    val overview: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "original_language") val originalLang: String?,
    val title: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    val popularity: String?,
    @Json(name = "vote_count") val voteCount: Int?,
    @Json(name = "vote_average") val voteAverage: String?,
    val video: Boolean?
) : Serializable

