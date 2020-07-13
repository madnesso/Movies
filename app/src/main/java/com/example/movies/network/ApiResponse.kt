package com.example.movies.network

import com.example.movies.recyc.Results

data class ApiResponse(
    val page: Int,
    val results: List<Results>,
    val total_results: Int,
    val total_pages: Int
)


