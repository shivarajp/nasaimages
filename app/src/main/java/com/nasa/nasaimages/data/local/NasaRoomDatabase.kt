package com.nasa.nasaimages.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NasaImageEntity::class], version = 1)
abstract class NasaRoomDatabase : RoomDatabase() {

    abstract fun getDao(): NasaDao

}