package com.example.movies

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.room.Room
import com.example.movies.data.db.MovieDatabase
import com.example.movies.data.network.*
import com.example.movies.data.repository.MoviesRepo
import com.example.movies.data.repository.MoviesRepoImpl
import com.example.movies.ui.movies.current.MovieFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

class MovieApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieApplication))
        bind() from singleton { MovieDatabase(instance()) }
        bind() from singleton { instance<MovieDatabase>().movieDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from provider { MovieFactory(instance()) }
        bind<MovieNetworkDataSource>() with singleton {
            MovieNetworkDataSourceImpl(instance())
        }
        bind<MoviesRepo>() with singleton { MoviesRepoImpl(instance(), instance()) }
        bind() from factory { movieRepo: MoviesRepo ->
            MovieFactory(instance())
        }


    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}