package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val movie: MovieCast,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}