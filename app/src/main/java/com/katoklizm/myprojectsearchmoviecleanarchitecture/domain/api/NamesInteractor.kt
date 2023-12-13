package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {

    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>

//    fun searchNames(expression: String, consumer: NamesConsumer)

//    interface NamesConsumer {
//        fun consume(foundNames: List<Person>?, errorMessage: String?)
//    }
}