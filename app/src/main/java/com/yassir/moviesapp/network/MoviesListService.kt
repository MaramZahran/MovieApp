package com.yassir.moviesapp.network

import com.yassir.moviesapp.data.Configuration
import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.MoviesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val MOVIE_ID_KEY = "movie_id"

interface MoviesListService {

    @GET("3/discover/movie")
    suspend fun fetchMovies(
        @Query(NetworkHelper.API_KEY) apiKey: String = NetworkHelper.API_KEY_VALUE
    ): MoviesList

    @GET("3/movie/{${MOVIE_ID_KEY}}")
    suspend fun fetchMovieDetails(
        @Path(MOVIE_ID_KEY) movieId: Int,
        @Query(NetworkHelper.API_KEY) apiKey: String = NetworkHelper.API_KEY_VALUE
    ): Movie

    @GET("3/configuration")
    suspend fun fetchConfiguration(
        @Query(NetworkHelper.API_KEY) apiKey: String = NetworkHelper.API_KEY_VALUE
    ): Configuration
}

