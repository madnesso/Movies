package com.example.movies.data.network

import androidx.lifecycle.LiveData
import com.example.movies.data.db.entity.ApiResponse
import com.example.movies.data.db.entity.MoviesEntry

interface MovieNetworkDataSource {
    val downloadedMovies: LiveData<ApiResponse>

    suspend fun fetchMovies(
        sort: String
    )
}