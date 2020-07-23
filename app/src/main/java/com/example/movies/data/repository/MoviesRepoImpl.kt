package com.example.movies.data.repository

import androidx.lifecycle.LiveData
import com.example.movies.data.db.MovieDao
import com.example.movies.data.db.entity.ApiResponse
import com.example.movies.data.db.unitlocalised.CleanerMovieEntry
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
        movieNetworkDataSource.apply {
            movieNetworkDataSource.downloadedMovies.observeForever {
                persistFetchedMovie(it)
            }
        }
    }

    override suspend fun getTopMovies(): LiveData<out List<CleanerMovieEntry>> {
        return withContext(Dispatchers.IO) {
            initTopMovies()
            return@withContext movieDao.getTopMovies()
        }
    }

    override suspend fun getPopMovies(): LiveData<out List<CleanerMovieEntry>> {
        return withContext(Dispatchers.IO) {
            initPopMovies()
            return@withContext movieDao.getPopMovies()
        }
    }

    private fun persistFetchedMovie(fetchedMovie: ApiResponse) {
        fun deleteOldData() {
            movieDao.deleteAllEntries()
        }
        GlobalScope.launch(Dispatchers.IO) {
            deleteOldData()
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
        movieNetworkDataSource.fetchMovies("popularity.desc")
    }

    private suspend fun fetchPopMovies() {
        movieNetworkDataSource.fetchMovies("vote_average.desc")
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}