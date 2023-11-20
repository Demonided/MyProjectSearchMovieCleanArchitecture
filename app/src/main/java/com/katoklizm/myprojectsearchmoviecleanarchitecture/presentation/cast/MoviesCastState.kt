package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast

import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.ui.RVItem
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}