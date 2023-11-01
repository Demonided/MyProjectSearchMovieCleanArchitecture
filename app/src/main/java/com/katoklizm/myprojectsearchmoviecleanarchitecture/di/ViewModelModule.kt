package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster.AboutViewModel
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
}