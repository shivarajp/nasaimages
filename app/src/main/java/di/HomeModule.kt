package di

import com.nasa.nasaimages.data.remote.NasaAPI
import com.nasa.nasaimages.data.remote.ResponseHandler
import com.nasa.nasaimages.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): NasaAPI{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(api: NasaAPI): HomeRepository{
        return HomeRepository(api)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler{
        return ResponseHandler()
    }

}