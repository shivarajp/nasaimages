package com.nasa.nasaimages.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.nasa.nasaimages.data.local.NasaDao
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.Resource
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.utils.toEntityList
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: NasaAPI,
    private val responseHandler: ResponseHandler,
    private val dao: NasaDao
) {

    suspend fun getNasaImages() {
        try {
            val res = responseHandler.handleSuccess(api.getNasaImages())
            res.data?.let {
                saveInDb(res.data)
            }
        } catch (exception: Exception) {
            //responseHandler.handleException(exception)
        }
    }

    fun getNasaImagesFromDb(): LiveData<List<NasaImageEntity>> {
        return dao.getAllNasaImages()

    /*liveData {
            try {
                responseHandler.handleSuccess(dao.getAllNasaImages())
            } catch (exception: Exception) {
                responseHandler.handleException(exception)
            }
        }*/
    }

    private fun saveInDb(nasaImages: NasaImagesResponseModel) {
        val list = mutableListOf<NasaImageEntity>()

        try {
            //val list = nasaImages.toEntityList()
            nasaImages.forEach {
                list.add(
                    NasaImageEntity(
                        imageTitle = it.title,
                        imageExplanation = it.explanation,
                        date = it.date,
                        imgUrl = it.url,
                        imgHdUrl = it.hdurl,
                        mediaType = it.mediaType,
                        serviceVersion = it.serviceVersion
                    )
                )
            }
        } catch (e: Exception) {
            println(e)
        }

        dao.insetNasaImage(list)
    }
}

