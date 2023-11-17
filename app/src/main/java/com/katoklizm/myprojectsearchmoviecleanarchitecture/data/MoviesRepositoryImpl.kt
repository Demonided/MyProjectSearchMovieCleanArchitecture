package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieCastConverter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastRequet
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie.LocalStorage
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCastPerson
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение интернета")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()

                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id))
                })
            }
            else -> {
                Resource.Error("Серверная ошибка")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(MovieDetails(id, title, imDbRating ?: "", year,
                        countries, genres, directors, writers, stars, plot))
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequet(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверить подключение к интернету")
            }
            200 -> {
                    Resource.Success(
                        data = movieCastConverter.convert(response as MovieCastResponse)
                    )
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }

        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(moviesId = movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(moviesId = movie.id)
    }
}