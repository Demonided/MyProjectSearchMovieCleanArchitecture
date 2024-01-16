package com.katoklizm.myprojectsearchmoviecleanarchitecture

import android.app.Application
import androidx.room.Room
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.AppDatabase
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.entity.MovieEntity
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.dataModule
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.interactorModule
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.navigationModule
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.repositoryModule
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.viewModelModule
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule, viewModelModule, repositoryModule, interactorModule)
        }

        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "New_movies.db")
            .allowMainThreadQueries()
            .build()

        val movieEntityList = listOf(
            MovieEntity("232", "РРыв", "234ав", "15ка", "12321ув1", false),
            MovieEntity("232", "РРыв", "234ав", "15ка", "12321ув1", false),
            // ... добавьте другие сущности
        )

        database.movieDao().insertMovies(movieEntityList)
    }
}