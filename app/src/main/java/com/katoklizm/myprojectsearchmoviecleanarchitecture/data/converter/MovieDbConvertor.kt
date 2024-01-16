package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.entity.MovieEntity
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDto
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

class MovieDbConvertor {

    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description, movie.isFavorite)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description, movie.isFavorite)
    }
}