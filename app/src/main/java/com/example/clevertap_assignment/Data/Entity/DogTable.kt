package com.example.clevertap_assignment.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class DogTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val imageUrl: String
)
