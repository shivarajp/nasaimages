package com.nasa.nasaimages.di

import android.content.Context
import androidx.room.Room
import com.nasa.nasaimages.data.local.NasaDao
import com.nasa.nasaimages.data.local.NasaRoomDatabase
import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): NasaAPI {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/shivarajp/5e5fbd4d90dca58a55b17747014d8bde/raw/dea5df59b878427dfd31cd330201423393ef8a23/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(
        api: NasaAPI,
        responseHandler: ResponseHandler,
        dao: NasaDao
    ): HomeRepository {
        return HomeRepository(api, responseHandler, dao)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }


    @Provides
    @Singleton
    fun provideNasaDao(@ApplicationContext context: Context): NasaDao {
        return Room.databaseBuilder(
            context.applicationContext,
            NasaRoomDatabase::class.java,
            "nasa_bd"
        ).build().getDao()
    }


}