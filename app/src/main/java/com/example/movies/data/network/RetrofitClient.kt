package com.example.movies.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofitClient: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofitClient == null)
            retrofitClient =
                Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        return retrofitClient!!
    }

}
