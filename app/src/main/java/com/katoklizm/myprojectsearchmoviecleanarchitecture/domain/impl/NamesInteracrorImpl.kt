package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import java.util.concurrent.Executors

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()
    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when(val resource = repository.searchNames(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }
}