package com.nasa.nasaimages.di

import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): NasaAPI{
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/shivarajp/5e5fbd4d90dca58a55b17747014d8bde/raw/dea5df59b878427dfd31cd330201423393ef8a23/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(api: NasaAPI, responseHandler: ResponseHandler): HomeRepository{
        return HomeRepository(api, responseHandler)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler{
        return ResponseHandler()
    }

}