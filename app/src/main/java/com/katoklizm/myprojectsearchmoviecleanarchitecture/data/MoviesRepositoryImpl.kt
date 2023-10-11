package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie.LocalStorage
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage
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

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(moviesId = movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(moviesId = movie.id)
    }
}