package com.nasa.nasaimages.data.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import com.nasa.nasaimages.data.remote.Resource
import com.nasa.nasaimages.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val imagesObserver = MutableLiveData<Resource<List<NasaImageEntity>>>()

    fun getImagesFromApi() {

        CoroutineScope(Dispatchers.IO).launch {
            repository.getNasaImages()
        }

        /*CoroutineScope(Dispatchers.IO).launch {
            imagesObserver.postValue(Resource.loading(null))
            imagesObserver.postValue(repository.getNasaImages())

        }

        return imagesObserver*/

    }

    fun getImagesFromLocalDb(): LiveData<List<NasaImageEntity>> {
        return repository.getNasaImagesFromDb()

    }
}