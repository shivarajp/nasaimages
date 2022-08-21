package com.nasa.nasaimages.repository

import androidx.lifecycle.MutableLiveData
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.Resource
import com.nasa.nasaimages.data.remote.ResponseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: NasaAPI, private val responseHandler: ResponseHandler) {

    suspend fun getNasaImages(): Resource<NasaImagesResponseModel> {
        return try {
            responseHandler.handleSuccess(api.getNasaImages())
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}