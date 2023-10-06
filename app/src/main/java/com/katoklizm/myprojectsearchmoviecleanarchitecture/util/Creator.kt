package com.katoklizm.myprojectsearchmoviecleanarchitecture.util

import android.app.Activity
import android.content.Context
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.MoviesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network.RetrofitNetworkClient
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl.MoviesInteractorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchPresenter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterController
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterPresenter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterView
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesView
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(
        context: Context,
    ): MoviesSearchPresenter {
        return MoviesSearchPresenter(
            context = context,
        )
    }

    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}