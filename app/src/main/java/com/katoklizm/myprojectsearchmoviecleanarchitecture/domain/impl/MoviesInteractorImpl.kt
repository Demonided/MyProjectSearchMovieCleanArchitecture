package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()
    override fun searchMovies(exception: String): Flow<Pair<List<Movie>?, String?>> {
        return repository.searchMovies(exception).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null)}
                is Resource.Error -> { Pair(null, result.message)}
            }
        }
    }

    override fun getMoviesDetails(movieId: String): Flow<Pair<MovieDetails?, String?>> {
        return repository.getMovieDetails(movieId).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null) }
                is Resource.Error -> { Pair(null, result.message)}
            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Pair<MovieCast?, String?>> {
        return repository.getMovieCast(movieId).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null) }
                is Resource.Error -> { Pair(null, result.message) }
            }
        }
    }

//    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
//        executor.execute {
//            when(val resource = repository.searchMovies(expression)) {
//                is Resource.Success -> { consumer.consume(resource.data, null) }
//                is Resource.Error -> { consumer.consume(null, resource.message) }
//            }
//        }
//    }
//
//    override fun getMoviesDetails(
//        movieId: String,
//        consumer: MoviesInteractor.MovieDetailsConsumer
//    ) {
//        executor.execute {
//            when(val resource = repository.getMovieDetails(movieId)) {
//                is Resource.Success -> { consumer.consume(resource.data, null) }
//                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
//            }
//        }
//    }
//
//    override fun getMovieCast(movieId: String, consumer: MoviesInteractor.MovieCastConsumer) {
//        executor.execute {
//            // Просто вызываем нужный метод Repository
//            when(val resource = repository.getMovieCast(movieId)) {
//                is Resource.Success -> { consumer.consume(resource.data, null) }
//                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
//            }
//        }
//    }

    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie = movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie = movie)
    }
}