package com.example.movies.data.repository

import androidx.lifecycle.LiveData
import com.example.movies.data.db.unitlocalised.CleanerMovieEntry

interface MoviesRepo {
    suspend fun getTopMovies(): LiveData<out List<CleanerMovieEntry>>
    suspend fun getPopMovies(): LiveData<out List<CleanerMovieEntry>>
}