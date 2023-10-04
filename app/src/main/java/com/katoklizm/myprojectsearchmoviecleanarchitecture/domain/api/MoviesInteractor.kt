package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }
}