package com.example.movies.data.network

import com.example.movies.data.recyc.Results

data class ApiResponse(
    val page: Int,
    val results: List<Results>,
    val total_results: Int,
    val total_pages: Int
)


