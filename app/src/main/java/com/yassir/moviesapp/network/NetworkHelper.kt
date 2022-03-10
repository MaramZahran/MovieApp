package com.yassir.moviesapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object NetworkHelper {
    const val API_KEY_VALUE = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    const val API_KEY = "api_key"
    const val MOVIES_LIST_BASE_URL = "https://api.themoviedb.org"

    private val logging = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit(baseUrl: String): Retrofit {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()

    }

}
