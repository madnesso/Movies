package com.example.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.databinding.ActivityResultBinding
import com.squareup.picasso.Picasso

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val image = intent.getStringExtra("image")
        val date = intent.getStringExtra("release date")
        binding.Title.text = title
        binding.overviewtext.text = overview
        binding.date.text = date
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${image}").centerCrop().fit()
            .into(binding.imageView)
    }
}