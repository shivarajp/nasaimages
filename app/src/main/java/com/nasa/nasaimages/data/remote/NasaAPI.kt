package com.nasa.nasaimages.data.remote

import retrofit2.http.GET

interface NasaAPI {

    @GET("/")
    suspend fun getNasaImages()

}