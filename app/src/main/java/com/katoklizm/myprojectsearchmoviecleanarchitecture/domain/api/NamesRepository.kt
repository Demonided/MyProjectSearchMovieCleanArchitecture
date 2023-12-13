package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {

    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}