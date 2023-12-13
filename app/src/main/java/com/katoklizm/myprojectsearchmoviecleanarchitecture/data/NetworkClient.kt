package com.katoklizm.myprojectsearchmoviecleanarchitecture.data

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
//    suspend fun doRequestSuspend(dto: Any): Response
}