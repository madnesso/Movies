package com.example.movies.data.repository

import androidx.lifecycle.LiveData
import com.example.movies.data.db.unitlocalised.CleanerMovie

interface MoviesRepo {
    suspend fun getTopMovies(): LiveData<out CleanerMovie>
    suspend fun getPopMovies(): LiveData<out CleanerMovie>
}