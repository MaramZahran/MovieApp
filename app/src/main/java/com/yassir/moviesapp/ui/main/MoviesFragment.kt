package com.yassir.moviesapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yassir.moviesapp.R
import com.yassir.moviesapp.data.Status
import com.yassir.moviesapp.databinding.MoviesFragmentBinding
import com.yassir.moviesapp.ui.BaseFragment

class MoviesFragment : BaseFragment() {

    private lateinit var binding: MoviesFragmentBinding
    private val moviesListAdaptor: MoviesListAdaptor = MoviesListAdaptor()
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(layoutInflater)
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        binding.rvMovies.adapter = moviesListAdaptor
        moviesListAdaptor.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(MOVIE_KEY, it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
        }

        viewModel.getMovies()

        viewModel.uiStatus.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {
                    moviesListAdaptor.updateList(it.data)
                    showList(true)
                }
                Status.ERROR -> showMessage(it.message.toString())
                Status.LOADING -> showMessage(getString(R.string.loading))
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolBar(getString(R.string.app_name), false)
    }

    private fun showMessage(message: String) {
        binding.message.text = message
        showList(false)
    }

    private fun showList(show: Boolean) {

        binding.rvMovies.visibility = if (show) View.VISIBLE else View.GONE
        binding.message.visibility = if (show) View.GONE else View.VISIBLE
    }


}