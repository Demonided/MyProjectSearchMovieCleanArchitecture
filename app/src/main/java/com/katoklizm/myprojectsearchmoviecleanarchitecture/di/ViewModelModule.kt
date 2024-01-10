package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast.MoviesCastViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.history.HistoryViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.names.NamesViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.poster.PosterViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster.AboutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel { (movieId: String) ->
        MoviesCastViewModel(movieId, get())
    }

    viewModel {
        NamesViewModel(androidContext(), get())
    }

    viewModel {
        HistoryViewModel(androidContext(), get())
    }
}