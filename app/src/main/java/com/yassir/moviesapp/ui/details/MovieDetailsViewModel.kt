package com.yassir.moviesapp.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.Resource
import com.yassir.moviesapp.network.repo.MoviesListRepo
import com.yassir.moviesapp.network.repo.MoviesListRepoImp
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsViewModel : ViewModel() {

    //TODO change the way of inilaization
    private val moviesDetailsRepo: MoviesListRepo = MoviesListRepoImp()
    private val _uiStatus = MutableLiveData<Resource<Movie>>(Resource.loading(null))
    val uiStatus: LiveData<Resource<Movie>> = _uiStatus

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                _uiStatus.value = Resource.success(moviesDetailsRepo.fetchMovieDetails(movieId))
            } catch (e: Exception) {
                _uiStatus.value = Resource.error(e.localizedMessage, null)
            }
        }
    }
}