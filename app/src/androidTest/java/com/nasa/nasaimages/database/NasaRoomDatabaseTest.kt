package com.nasa.nasaimages.database

import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nasa.nasaimages.data.local.NasaDao
import com.nasa.nasaimages.data.local.NasaRoomDatabase
import com.nasa.nasaimages.database.TestDataGenerator.getImageModelsList
import com.nasa.nasaimages.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NasaRoomDatabaseTest {

    lateinit var database: NasaRoomDatabase
    private lateinit var dao: NasaDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), NasaRoomDatabase::class.java
        ).build()
        dao = database.getDao()
    }


    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_insert_image() {
        val data = getImageModelsList()
        dao.insetNasaImage(data)
        val images = dao.getAllNasaImages().getOrAwaitValue()

        assertThat(images.size).isEqualTo(3)


    }

    @Test
    fun test_get_images_test() {
        val data = getImageModelsList()
        dao.insetNasaImage(data)
        val images = dao.getAllNasaImages().getOrAwaitValue()

        assertThat(images.size).isEqualTo(3)
        assertThat(images[0].date).isEqualTo("date0")
        assertThat(images[1].date).isEqualTo("date1")
        assertThat(images[2].date).isEqualTo("date2")
    }

}