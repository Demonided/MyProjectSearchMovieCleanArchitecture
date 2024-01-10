package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.HistoryRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.MoviesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.NamesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieCastConverter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieDbConvertor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db.HistoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get(), get(), get())
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

    factory { MovieDbConvertor() }

    single<HistoryRepository> {
        HistoryRepositoryImpl(get(), get())
    }
}