package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieCastConverter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieDbConvertor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.AppDatabase
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDto
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie.LocalStorage
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor,
) : MoviesRepository {

    override fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequestSuspend(MoviesSearchRequest(expression))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение интернета"))
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()
                with(response as MoviesSearchResponse) {
                    val data = results.map {
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description,
                            inFavorite = stored.contains(it.id)
                        )
                    }
                    saveMovie(results)
                    emit(Resource.Success(data))
                }


//                Resource.Success((response as MoviesSearchResponse).results.map {
//                    Movie(
//                        id = it.id,
//                        resultType = it.resultType,
//                        image = it.image,
//                        title = it.title,
//                        description = it.description,
//                        inFavorite = stored.contains(it.id)
//                    )
//                })
            }

            else -> {
                emit(Resource.Error("Серверная ошибка"))
            }
        }
    }


    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> = flow {
        val response = networkClient.doRequestSuspend(MovieDetailsRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    emit(Resource.Success(
                        MovieDetails(
                            id, title, imDbRating ?: "", year,
                            countries, genres, directors, writers, stars, plot
                        )
                    ))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))

            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        val response = networkClient.doRequestSuspend(MovieCastRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверить подключение к интернету"))
            }

            200 -> {
                emit(Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                ))
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }

        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(moviesId = movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(moviesId = movie.id)
    }

    private suspend fun saveMovie(movie: List<MovieDto>) {
        val movieEntities = movie.map { movie -> movieDbConvertor.map(movie) }
        appDatabase.movieDao().insertMovies(movieEntities)
    }
}