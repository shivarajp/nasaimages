package com.nasa.nasaimages.repository

import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.ResponseHandler

class HomeRepository(val api: NasaAPI, val responseHandler: ResponseHandler) {

    suspend fun getNasaImages(){

    }

}