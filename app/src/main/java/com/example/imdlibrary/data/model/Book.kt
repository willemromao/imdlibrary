package com.example.imdlibrary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val year: Int,
    val description: String,
    val imageUrl: String
)
