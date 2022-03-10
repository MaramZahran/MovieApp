package com.yassir.moviesapp.network.repo

import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.MoviesList
import com.yassir.moviesapp.network.MoviesListService
import com.yassir.moviesapp.network.NetworkHelper

class MoviesListRepoImp : MoviesListRepo {

    private val retrofit: MoviesListService by lazy {
        with(NetworkHelper) {
            retrofit(MOVIES_LIST_BASE_URL)
                .create(MoviesListService::class.java)
        }
    }

    override suspend fun fetchMovies(): MoviesList = retrofit.fetchMovies()
    override suspend fun fetchMovieDetails(movieId: Int): Movie =
        retrofit.fetchMovieDetails(movieId)


}