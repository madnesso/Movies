package com.example.movies.ui.movies.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.data.repository.MoviesRepo

@Suppress("UNCHECKED_CAST")
class MovieFactory(
    private val moviesRepo: MoviesRepo
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentMoviesViewModel(
            moviesRepo
        ) as T
    }
}