package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.dao.MovieDao
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}