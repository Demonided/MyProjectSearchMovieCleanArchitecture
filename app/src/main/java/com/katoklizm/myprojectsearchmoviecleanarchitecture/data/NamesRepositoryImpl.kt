package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.NamesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.NamesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesRepository
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    //    override fun searchNames(expression: String): Resource<List<Person>> {
//        val response = networkClient.doRequest(NamesSearchRequest(expression))
//        return when(response.resultCode) {
//            -1 -> {
//                Resource.Error("Проверьте подключение к интернету")
//            }
//            200 -> {
//                with(response as NamesSearchResponse) {
//                    Resource.Success(results.map {
//                        Person(id = it.id,
//                            name = it.title,
//                            description = it.description,
//                            photoUrl = it.image)
//                    })
//                }
//            }
//            else -> {
//                Resource.Error("Ошибка сервера")
//            }
//        }
//    }
    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequestSuspend(NamesSearchRequest(expression))
        when(response.resultCode) {
            1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as NamesSearchResponse) {
                    val data = results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

}