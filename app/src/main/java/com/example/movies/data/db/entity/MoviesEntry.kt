package com.example.movies.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MoviesEntry(
    val poster_path: String?,
    val overview: String,
    val release_date: String,
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}