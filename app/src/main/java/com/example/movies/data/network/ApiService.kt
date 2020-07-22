package com.example.movies.data.network

import com.example.movies.data.db.entity.ApiResponse
import retrofit2.Call
import retrofit2.http.*

//const val apikey: String = "25193f454c3e24f2891f790aed91f518"

interface ApiService {

    @GET("movie/popular")
    fun getPopMovies(
        @Query("api_key") apiKey: String = "25193f454c3e24f2891f790aed91f518"
    ): Call<ApiResponse>

    @GET("movie/top_rated")
    fun getTopMovies(
        @Query("api_key") apiKey: String = "25193f454c3e24f2891f790aed91f518"
    ): Call<ApiResponse>

}