package com.katoklizm.myprojectsearchmoviecleanarchitecture

import android.app.Application
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.dataModule
import com.katoklizm.myprojectsearchmoviecleanarchitecture.di.interactorModule
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

    }
}