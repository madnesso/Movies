package com.example.movies.data.db.unitlocalised

import android.icu.text.CaseMap
import androidx.room.ColumnInfo

data class CleanerMovie(
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String
)