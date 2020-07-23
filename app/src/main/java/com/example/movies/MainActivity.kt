package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.data.network.ApiService
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

private val tag: String = MainActivity::class.java.simpleName
private lateinit var binding: ActivityMainBinding
const val apikey: String = "25193f454c3e24f2891f790aed91f518"
private lateinit var apiService: ApiService

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}