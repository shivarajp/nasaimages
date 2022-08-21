package com.nasa.nasaimages.data.remote

import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import retrofit2.http.GET

interface NasaAPI {

    @GET("nasaimages")
    suspend fun getNasaImages() : NasaImagesResponseModel

}