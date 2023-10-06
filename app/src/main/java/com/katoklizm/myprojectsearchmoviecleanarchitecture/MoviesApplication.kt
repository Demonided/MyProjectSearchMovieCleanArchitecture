package com.katoklizm.myprojectsearchmoviecleanarchitecture

import android.app.Application
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchPresenter

class MoviesApplication: Application() {

    var moviesSearchPresenter: MoviesSearchPresenter? = null
}