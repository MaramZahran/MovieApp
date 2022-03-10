package com.yassir.moviesapp.ui

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yassir.moviesapp.R

open class BaseFragment : Fragment() {

    protected fun updateToolBar(title: String, showBack: Boolean) {
        activity?.let {
            if (it is AppCompatActivity) {
                it.supportActionBar?.let { it ->
                    it.title = title
                    it.setDisplayHomeAsUpEnabled(showBack)
                    it.setHomeButtonEnabled(showBack)
                }
            }
        }

    }

    companion object UIHelper {
        const val MOVIE_KEY = "movie"

        fun loadImage(image: ImageView, imageUrl: String) {
            Glide.with(image.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .error(R.drawable.placeholder)
                .into(image)
        }
    }
}