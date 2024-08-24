package com.example.clevertap_assignment.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clevertap_assignment.Data.DAO.TableDAO
import com.example.clevertap_assignment.Data.Entity.DogTable

@Database(entities = [DogTable::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun TableDAO(): TableDAO

    companion object {
        @Volatile
        private var instance: DogDatabase? = null

        fun getInstance(context: Context): DogDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DogDatabase::class.java,
                    "dog_image_database"
                )
                    .build().also { instance = it }
            }
        }

    }
}