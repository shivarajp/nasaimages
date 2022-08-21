package com.nasa.nasaimages.data.views

import androidx.lifecycle.ViewModel
import com.nasa.nasaimages.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel(val repository: HomeRepository) : ViewModel() {
}