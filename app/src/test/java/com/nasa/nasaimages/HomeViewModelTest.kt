package com.nasa.nasaimages

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.filters.LargeTest
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.util.TestDataGenerator.getImageModelsList
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.repository.HomeRepository
import com.nasa.nasaimages.util.MainCoroutineRule
import com.nasa.nasaimages.views.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@LargeTest
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var repository: HomeRepository

    private lateinit var viewModel: HomeViewModel
    private lateinit var responseHandler: ResponseHandler

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        responseHandler = ResponseHandler()
        viewModel = HomeViewModel(repository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_get_images_from_api() = runTest {

        val imagesObserver = MutableLiveData<List<NasaImageEntity>>()
        imagesObserver.value = getImageModelsList()

        Mockito.`when`(
            repository.getNasaImages()
        ).thenReturn(imagesObserver)

        viewModel.getImagesFromApi()

        viewModel.imagesObserver.observeForTesting {
            assert(viewModel.imagesObserver.value != null)
        }
    }


    @Test
    fun test_get_images_from_db() = runTest {

        val imagesObserver = MutableLiveData<List<NasaImageEntity>>()
        imagesObserver.value = getImageModelsList()

        Mockito.`when`(
            repository.getNasaImagesFromDb()
        ).thenReturn(imagesObserver)
        val data = viewModel.getImagesFromLocalDb().getOrAwaitValue()

        assert(data.size == 3)

    }
}