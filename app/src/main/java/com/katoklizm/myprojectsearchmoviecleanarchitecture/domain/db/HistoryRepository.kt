package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun historyMovies(): Flow<List<Movie>>
}