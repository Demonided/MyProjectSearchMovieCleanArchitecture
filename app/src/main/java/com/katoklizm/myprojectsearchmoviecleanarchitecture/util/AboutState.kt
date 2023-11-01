package com.katoklizm.myprojectsearchmoviecleanarchitecture.util

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}