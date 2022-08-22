package com.muhammedfatihcelik.foodorder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muhammedfatihcelik.foodorder.data.entity.address.City
import com.muhammedfatihcelik.foodorder.data.entity.address.District

@Database(entities = [City::class, District::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

    companion object {
        @Volatile private var instance : LocalDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDataBase(context).also {
                instance = it
            }
        }
        private fun createDataBase(context: Context) = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "LocalDatabase").build()
    }

}