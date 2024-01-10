package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.history

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie

sealed interface HistoryState {

    object Loading: HistoryState

    data class Content(
        val movies: List<Movie>
    ) : HistoryState

    data class Empty(
        val message: String
    ) : HistoryState
}