package com.example.movies.data.repository

import androidx.lifecycle.LiveData
import com.example.movies.data.db.MovieDao
import com.example.movies.data.db.entity.ApiResponse
import com.example.movies.data.db.unitlocalised.CleanerMovie
import com.example.movies.data.network.MovieNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class MoviesRepoImpl(
    private val movieDao: MovieDao,
    private val movieNetworkDataSource: MovieNetworkDataSource
) : MoviesRepo {
    init {
        movieNetworkDataSource.downloadedMovies.observeForever {
            presistFetchedMovie(it)
        }
    }

    override suspend fun getTopMovies(): LiveData<out CleanerMovie> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDao.getTopMovies()
        }
    }

    override suspend fun getPopMovies(): LiveData<out CleanerMovie> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDao.getPopMovies()
        }
    }

    private fun presistFetchedMovie(fetchedMovie: ApiResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            movieDao.upsert(fetchedMovie.results)
        }
    }

    private suspend fun initPopMovies() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1)))
            fetchPopMovies()
    }

    private suspend fun initTopMovies() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1)))
            fetchTopMovies()
    }

    private suspend fun fetchTopMovies() {
        movieNetworkDataSource.fetchTopMovies()
    }

    private suspend fun fetchPopMovies() {
        movieNetworkDataSource.fetchPopMovies()
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}