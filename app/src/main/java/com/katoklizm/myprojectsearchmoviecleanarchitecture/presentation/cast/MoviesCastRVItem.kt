package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast

import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.ui.RVItem
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCastPerson

sealed interface MoviesCastRVItem: RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}