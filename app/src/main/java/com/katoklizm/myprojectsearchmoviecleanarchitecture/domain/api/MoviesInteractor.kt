package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }
}