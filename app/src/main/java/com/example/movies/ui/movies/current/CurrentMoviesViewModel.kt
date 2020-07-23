package com.example.movies.ui.movies.current

import androidx.lifecycle.ViewModel
import com.example.movies.data.repository.MoviesRepo
import com.example.movies.lazyDeferred

class CurrentMoviesViewModel(
    private val moviesRepo: MoviesRepo
) : ViewModel() {
    val moviesEntries by lazyDeferred {
        moviesRepo.getPopMovies()
    }
}