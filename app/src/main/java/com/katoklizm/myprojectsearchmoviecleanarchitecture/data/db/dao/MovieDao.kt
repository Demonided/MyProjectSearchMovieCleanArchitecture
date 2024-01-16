package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table")
    suspend fun getMovies(): List<MovieEntity>


}