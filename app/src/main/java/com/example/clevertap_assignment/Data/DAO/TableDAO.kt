package com.example.clevertap_assignment.Data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clevertap_assignment.Data.Entity.DogTable

@Dao
interface TableDAO {

    @Insert
    suspend fun insert(url: DogTable): Long

    @Query("Delete from dog_table")
    suspend fun deleteImages()

    @Query("select * from dog_table where id=:id")
    suspend fun getImageModelFromID(id: Int): DogTable

    @Query("select * from dog_table where id>:id order by id ASC limit 1")
    suspend fun getNextImage(id: Int): DogTable?

    @Query("select * from dog_table where id<:id order by id DESC limit 1")
    suspend fun getPrviousImage(id: Int): DogTable?

    @Query("Delete from sqlite_sequence where name='dog_table'")
    suspend fun resetIDtoZero()

    @Query("select * from dog_table order by id ASC limit 1")
    suspend fun getFirstImage(): DogTable

}