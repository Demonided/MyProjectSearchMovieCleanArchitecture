package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.Response

interface NetworkClient {

    suspend fun doRequestSuspend(dto: Any): Response

//    fun doRequest(dto: Any): Response
}