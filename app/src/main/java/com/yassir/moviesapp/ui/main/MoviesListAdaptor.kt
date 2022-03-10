package com.yassir.moviesapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.MoviesList
import com.yassir.moviesapp.databinding.MovieListItemBinding
import com.yassir.moviesapp.network.repo.ConfigurationRepoImp
import com.yassir.moviesapp.ui.BaseFragment

class MoviesListAdaptor : RecyclerView.Adapter<MoviesListAdaptor.MovieHolder>() {

    private var moviesListResult: MoviesList = MoviesList(0, 0, 0, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieListItemBinding =
            MovieListItemBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = moviesListResult.results!![position]
        holder.binding.apply {
            movieTitle.text = movie.title
            movieReleaseYear.text = movie.releaseDate

            BaseFragment.loadImage(
                movieImage, ConfigurationRepoImp
                    .ImageConfiguration
                    .getFullImageUrl(
                        movie.posterPath, ConfigurationRepoImp.ImageType.POSTER
                    ).toString()
            )

            root.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesListResult.results?.size ?: 0
    }

    fun updateList(updatedList: MoviesList?) {
        updatedList?.let {
            moviesListResult = updatedList
            notifyDataSetChanged()
        }
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    class MovieHolder(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}



