package com.katoklizm.myprojectsearchmoviecleanarchitecture

import android.app.Application
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel

class MoviesApplication: Application() {

    var moviesSearchPresenter: MoviesSearchViewModel? = null
}