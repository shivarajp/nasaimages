package com.nasa.nasaimages.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.nasa.nasaimages.data.local.NasaDao
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.Resource
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.utils.toEntityList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: NasaAPI,
    private val dao: NasaDao
) {

    fun getNasaImages(): LiveData<List<NasaImageEntity>> {

        CoroutineScope(Dispatchers.IO).launch {
            saveInDb(api.getNasaImages())
        }
        return getNasaImagesFromDb()
    }

    fun getNasaImagesFromDb(): LiveData<List<NasaImageEntity>> {
        return dao.getAllNasaImages()
    }

    fun saveInDb(nasaImages: NasaImagesResponseModel) {
        try {
            val list = nasaImages.toEntityList()
            dao.insetNasaImage(list)
        } catch (e: Exception) {
            println(e)
        }
    }
}

