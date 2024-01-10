package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db.HistoryInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.db.HistoryRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {
    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }
}