package com.yassir.moviesapp.network.repo

import com.yassir.moviesapp.data.Configuration

interface ConfigurationRepo {
    suspend fun fetchConfiguration(): Configuration
}