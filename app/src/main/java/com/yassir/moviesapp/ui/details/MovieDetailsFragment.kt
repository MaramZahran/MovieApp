package com.yassir.moviesapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yassir.moviesapp.R
import com.yassir.moviesapp.data.Movie
import com.yassir.moviesapp.data.Status
import com.yassir.moviesapp.databinding.MovieDetailsFragmentBinding
import com.yassir.moviesapp.network.repo.ConfigurationRepoImp
import com.yassir.moviesapp.ui.BaseFragment
import kotlinx.coroutines.launch

class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsFragmentBinding.inflate(layoutInflater)
        arguments?.get(MOVIE_KEY)?.let {
            movie = it as Movie
            updateUI()
        }
        lifecycleScope.launch {
            movie?.let {
                viewModel.getMovieDetails(it.id)
            }
        }
        viewModel.uiStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> updateUI()
                //TODO show Dialog and close the screen
                Status.ERROR -> showMessage(it.message.toString())
                Status.LOADING -> showMessage(getString(R.string.loading))
            }
        }
        return binding.root
    }

    private fun updateUI() {
        showDetails(true)
        updateToolBar(movie?.title.toString(), true)
        binding.apply {
            movieOverview.text = movie?.overview
            movieTitle.text = movie?.title
            movieReleaseYear.text = movie?.releaseDate
            loadImage(
                movieImage, ConfigurationRepoImp.ImageConfiguration.getFullImageUrl(
                    movie?.posterPath,
                    ConfigurationRepoImp.ImageType.POSTER
                ).toString()
            )
        }
    }

    private fun showMessage(message: String) {
        binding.movieTitle.text = message
        showDetails(false)
    }

    private fun showDetails(show: Boolean) {
        binding.movieImage.visibility = if (show) View.VISIBLE else View.GONE
        binding.movieReleaseYear.visibility = if (show) View.VISIBLE else View.GONE
        binding.movieOverview.visibility = if (show) View.VISIBLE else View.GONE
    }

}