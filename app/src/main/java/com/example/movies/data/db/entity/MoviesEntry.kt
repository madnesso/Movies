package com.example.movies.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Movies", indices = [Index(value = ["title"], unique = true)])
data class MoviesEntry(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val poster_path: String?,
    val overview: String,
    val release_date: String,
    val title: String
)