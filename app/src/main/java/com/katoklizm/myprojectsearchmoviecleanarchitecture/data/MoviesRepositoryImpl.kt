package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение интернета")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)
                })
            }
            else -> {
                Resource.Error("Серверная ошибка")
            }
        }
    }
}