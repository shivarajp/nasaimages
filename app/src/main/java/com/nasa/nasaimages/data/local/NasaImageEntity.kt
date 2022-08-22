package com.nasa.nasaimages.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nasa_image")
data class NasaImageEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "title")
    var imageTitle: String,

    @ColumnInfo(name = "explanation")
    var imageExplanation: String,


    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "url")
    var imgUrl: String,

    @ColumnInfo(name = "hdurl")
    var imgHdUrl: String,

    @ColumnInfo(name = "media_type")
    var mediaType: String,

    @ColumnInfo(name = "service_version")
    var serviceVersion: String

)