package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.MoviesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieCastConverter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get())
    }

}