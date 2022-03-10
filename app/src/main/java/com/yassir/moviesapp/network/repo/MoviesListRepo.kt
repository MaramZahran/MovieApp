package com.yassir.moviesapp.network.repo

import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.MoviesList

interface MoviesListRepo {
    suspend fun fetchMovies(): MoviesList
    suspend fun fetchMovieDetails(movieId: Int): Movie
}