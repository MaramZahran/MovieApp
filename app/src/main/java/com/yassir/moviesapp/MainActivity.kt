package com.yassir.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yassir.moviesapp.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.navigationIcon =
            getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryColor)
    }
}