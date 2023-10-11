package com.katoklizm.myprojectsearchmoviecleanarchitecture.util

import android.content.Context
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.MoviesRepositoryImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie.LocalStorage
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network.RetrofitNetworkClient
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl.MoviesInteractorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterPresenter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(
            RetrofitNetworkClient(context),
            LocalStorage(context.getSharedPreferences("local_storage", Context.MODE_PRIVATE))
            )
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

//    fun provideMoviesSearchViewModel(
//        context: Context,
//    ): MoviesSearchViewModel {
//        return MoviesSearchViewModel(
//            application = context,
//        )
//    }

    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}