package com.nasa.nasaimages.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import com.nasa.nasaimages.data.models.NasaImagesResponseModelItem

@Dao
interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insetNasaImage(images: MutableList<NasaImageEntity>)

    @Query("select * from nasa_image")
    fun getAllNasaImages(): LiveData<List<NasaImageEntity>>

}