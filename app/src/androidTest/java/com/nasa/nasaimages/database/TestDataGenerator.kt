package com.nasa.nasaimages.database

import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.utils.toEntityList

object TestDataGenerator {


    fun getImageModelsList(): MutableList<NasaImageEntity> {
        val list = mutableListOf<NasaImageEntity>()

        for (i in 0..2) {
            list.add(
                NasaImageEntity(
                    imageTitle = "title$i",
                    imageExplanation = "explanation$i",
                    copyright = "$i",
                    date = "date$i",
                    imgUrl = "url$i",
                    imgHdUrl = "hdurl$i",
                    mediaType = "mediaType$i",
                    serviceVersion = "serviceVersion$i"
                )
            )
        }
        return list
    }
}

