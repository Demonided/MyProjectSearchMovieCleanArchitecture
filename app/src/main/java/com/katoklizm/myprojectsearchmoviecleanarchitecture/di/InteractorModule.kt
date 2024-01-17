package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db.HistoryInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl.HistoryInteractorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl.MoviesInteractorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl.NamesInteracrorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<NamesInteractor> {
        NamesInteracrorImpl(get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl(get())
    }


}