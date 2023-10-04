package com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.impl

import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }

//    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
//        val t = Thread {
//            consumer.consume(repository.searchMovies(expression))
//        }
//        t.start()
//    }
}