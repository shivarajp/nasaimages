package com.nasa.nasaimages.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.remote.Resource
import com.nasa.nasaimages.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    lateinit var imagesObserver : LiveData<List<NasaImageEntity>>

    fun getImagesFromApi(): LiveData<List<NasaImageEntity>> {
        imagesObserver = repository.getNasaImages()
        return imagesObserver
    }

    fun getImagesFromLocalDb(): LiveData<List<NasaImageEntity>> {
        return repository.getNasaImagesFromDb()

    }
}