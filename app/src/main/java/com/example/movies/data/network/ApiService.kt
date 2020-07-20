package com.example.movies.data.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular")
    fun getPopMovies(
        @Query("api_key") apiKey: String
    ): Call<ApiResponse>

    @GET("movie/top_rated")
    fun getTopMovies(
        @Query("api_key") apiKey: String
    ): Call<ApiResponse>

}