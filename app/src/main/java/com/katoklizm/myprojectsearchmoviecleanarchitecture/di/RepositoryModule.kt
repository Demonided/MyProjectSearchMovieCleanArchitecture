package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.MoviesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

}