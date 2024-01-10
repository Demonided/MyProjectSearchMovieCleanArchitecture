package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {

    fun historyMovies(): Flow<List<Movie>>
}