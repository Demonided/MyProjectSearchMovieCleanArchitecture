package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.converter.MovieDbConvertor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.AppDatabase
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.entity.MovieEntity
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db.HistoryRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor
) : HistoryRepository {
    override fun historyMovies(): Flow<List<Movie>> = flow {
        val movies = appDatabase.movieDao().getMovies()
        emit(convertFromMovieEntity(movies))
    }

    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> {
        return movies.map { movie -> movieDbConvertor.map(movie) }
    }
}