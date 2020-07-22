package com.example.movies.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.data.db.entity.ApiResponse
import retrofit2.await
import java.lang.Exception

class MovieNetworkDataSourceImpl(
    private val apiService: ApiService
) : MovieNetworkDataSource {
    private val _downloadedMovies = MutableLiveData<ApiResponse>()
    override val downloadedMovies: LiveData<ApiResponse>
        get() = _downloadedMovies

    override suspend fun fetchMovies(sort: String) {
        try {
            val fetchPopMovies = apiService.getMovies(sort).await()
            _downloadedMovies.postValue(fetchPopMovies)
        } catch (e: Exception) {
            Log.e("connectivity", "NO SERVICE", e)
        }
    }

}