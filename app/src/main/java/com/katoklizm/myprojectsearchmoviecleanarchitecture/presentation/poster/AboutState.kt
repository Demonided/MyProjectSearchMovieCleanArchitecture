package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.poster

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}