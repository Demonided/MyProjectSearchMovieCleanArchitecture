package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

//    private val executor = Executors.newCachedThreadPool()
//
//    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
//        executor.execute {
//            when(val resource = repository.searchNames(expression)) {
//                is Resource.Success -> { consumer.consume(resource.data, null) }
//                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
//            }
//        }
//    }
}