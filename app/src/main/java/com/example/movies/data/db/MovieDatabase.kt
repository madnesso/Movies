package com.example.movies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movies.data.db.entity.MoviesEntry

@Database(
    entities = [MoviesEntry::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instace: MovieDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {
            instace ?: buildDatabase(context).also { instace = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "Movies.db"
            ).build()
    }
}