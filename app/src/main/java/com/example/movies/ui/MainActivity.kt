package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.network.ApiResponse
import com.example.movies.network.ApiService
import com.example.movies.network.RetrofitClient
import com.example.movies.recyc.MoviesAdapter
import com.example.movies.recyc.Results
import kotlinx.android.synthetic.main.activity_main.*
import  retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

private val tag: String = MainActivity::class.java.simpleName
private lateinit var binding: ActivityMainBinding
const val apikey: String = "25193f454c3e24f2891f790aed91f518"
private lateinit var apiService: ApiService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getPopMovies()
        binding.popular.setOnClickListener(View.OnClickListener {
            binding.toptext.text = "Popular"
            getTopRatedMovies()
        })
        binding.toprated.setOnClickListener(View.OnClickListener {
            binding.toptext.text = "Top rated"
            getPopMovies()
        })
    }

    private fun getTopRatedMovies() {
        apiService = RetrofitClient.getClient().create(ApiService::class.java)

        apiService.getTopMovies(apikey).enqueue(object : Callback<ApiResponse> {

            override fun onResponse(
                call: Call<ApiResponse>, response: Response<ApiResponse>
            ) {

                if (response.isSuccessful) {
                    val moviesList: List<Results> = response.body()?.results ?: listOf()
                    binding.moviesrecycler.adapter = MoviesAdapter(applicationContext, moviesList)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(tag, t.message.toString())
            }

        })
    }

    private fun getPopMovies() {
        apiService = RetrofitClient.getClient().create(ApiService::class.java)

        apiService.getPopMovies(apikey).enqueue(object : Callback<ApiResponse> {

            override fun onResponse(
                call: Call<ApiResponse>, response: Response<ApiResponse>
            ) {

                if (response.isSuccessful) {
                    val moviesList: List<Results> = response.body()?.results ?: listOf()
                    moviesrecycler.adapter = MoviesAdapter(applicationContext, moviesList)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(tag, t.message.toString())
                Toast.makeText(applicationContext, "something is wrong", Toast.LENGTH_SHORT).show()
            }

        })

    }
}