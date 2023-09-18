package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}