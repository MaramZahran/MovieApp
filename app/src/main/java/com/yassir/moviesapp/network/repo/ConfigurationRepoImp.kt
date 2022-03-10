package com.yassir.moviesapp.network.repo

import android.util.Log
import com.yassir.moviesapp.data.Configuration
import com.yassir.moviesapp.data.Image
import com.yassir.moviesapp.network.MoviesListService
import com.yassir.moviesapp.network.NetworkHelper
import kotlinx.coroutines.*

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

        fun initializeConfiguration() {

            try {


                val scope = CoroutineScope(Dispatchers.IO + Job())
                scope.launch {
                    try {
                        val configJob = async(Dispatchers.IO) {
                            configRepo.fetchConfiguration()
                        }
                        imageConfiguration = configJob.await().images
                    } catch (e: Exception) {
                        Log.e("Movie", e.message.toString(), e)

                    }
                }
            } catch (e: Exception) {
                Log.e("Movie", e.message.toString(), e)
            }

        }

        fun getFullImageUrl(imagePath: String?, type: ImageType): String? {

            imagePath?.let {
                val relativePath: String?

                when (type) {
                    ImageType.BACKDROP -> relativePath =
                        imageConfiguration.backdropSizes?.firstOrNull()
                    ImageType.LOGO -> relativePath = imageConfiguration.logoSizes?.firstOrNull()
                    ImageType.POSTER -> relativePath = imageConfiguration.posterSizes?.firstOrNull()

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