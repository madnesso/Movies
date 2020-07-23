package com.example.movies.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.db.entity.MoviesEntry
import com.example.movies.data.db.unitlocalised.CleanerMovieEntry

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(moviesEntry: List<MoviesEntry>)

    @Query(value = "select * from  Movies")
    fun getTopMovies(): LiveData<List<CleanerMovieEntry>>

    @Query(value = "select * from  Movies")
    fun getPopMovies(): LiveData<List<CleanerMovieEntry>>

    @Query(value = "delete from Movies")
    fun deleteAllEntries()
}