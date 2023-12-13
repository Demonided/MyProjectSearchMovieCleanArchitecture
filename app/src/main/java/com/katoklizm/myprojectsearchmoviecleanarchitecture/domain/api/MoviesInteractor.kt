package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface MoviesInteractor {
    fun searchMovies(exception: String): Flow<Pair<List<Movie>?, String?>>

    fun getMoviesDetails(movieId: String): Flow<Pair<MovieDetails?, String?>>

    fun getMovieCast(movieId: String): Flow<Pair<MovieCast?, String?>>
//    fun searchMovies(expression: String, consumer: MoviesConsumer)
//
//    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)
//
//    fun getMovieCast(movieId: String, consumer: MovieCastConsumer)

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)


//    interface MoviesConsumer {
//        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
//    }
//
//    interface MovieDetailsConsumer {
//        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
//    }
//
//    interface MovieCastConsumer {
//        fun consume(movieCast: MovieCast?, errorMessage: String?)
//    }
}