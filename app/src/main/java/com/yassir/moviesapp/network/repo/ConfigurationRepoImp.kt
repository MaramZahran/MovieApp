package com.yassir.moviesapp.network.repo

import com.yassir.moviesapp.data.Configuration
import com.yassir.moviesapp.data.Image
import com.yassir.moviesapp.network.MoviesListService
import com.yassir.moviesapp.network.NetworkHelper

class ConfigurationRepoImp : ConfigurationRepo {

    private val retrofit: MoviesListService by lazy {
        with(NetworkHelper) {
            retrofit(MOVIES_LIST_BASE_URL)
                .create(MoviesListService::class.java)
        }
    }

    override suspend fun fetchConfiguration(): Configuration = retrofit.fetchConfiguration()

    object ImageConfiguration {
        private lateinit var imageConfiguration: Image
        private val configRepo: ConfigurationRepo = ConfigurationRepoImp()

        fun getFullImageUrl(imagePath: String?, type: ImageType): String? {

            imagePath?.let {

                val relativePath: String? = when (type) {
                    ImageType.BACKDROP -> imageConfiguration.backdropSizes?.firstOrNull()
                    ImageType.LOGO -> imageConfiguration.logoSizes?.firstOrNull()
                    ImageType.POSTER -> imageConfiguration.posterSizes?.firstOrNull()

                }
                return "${imageConfiguration.secureBaseUrl}$relativePath$imagePath"
            }

            return imagePath
        }

        suspend fun initConfiguration() {
            imageConfiguration = configRepo.fetchConfiguration().images
        }
    }


    enum class ImageType {
        LOGO, BACKDROP, POSTER
    }
}