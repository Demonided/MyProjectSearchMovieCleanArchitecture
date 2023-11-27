package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.names

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person

sealed interface NamesState{

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}