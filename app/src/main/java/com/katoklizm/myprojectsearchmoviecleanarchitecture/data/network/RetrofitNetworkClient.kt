package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.NetworkClient
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.NamesSearchRequest
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val imdbService: IMDbApiService,
    private val context: Context
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MoviesSearchRequest)
            && (dto !is MovieDetailsRequest)
            && (dto !is MovieCastRequest)
            && (dto !is NamesSearchRequest)) {
            return Response().apply { resultCode = 400 }
        }

//        if ((dto !is NamesSearchRequest)) {
//            return Response().apply { resultCode = 400 }
//        }

        return withContext(Dispatchers.IO) {
            try {
                val response = when (dto) {
                    is NamesSearchRequest -> async { imdbService.searchName(dto.expression) }
                    is MoviesSearchRequest -> async { imdbService.findMovies(dto.expression) }
                    is MovieDetailsRequest -> async { imdbService.getMovieDetails(dto.movieId) }
                    else -> async { imdbService.getFullCast((dto as MovieCastRequest).movieId) }
                }.await()
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }


//        val response = when (dto) {
//            is NamesSearchRequest -> imdbService.searchName(dto.expression).execute()
//            is MoviesSearchRequest -> imdbService.findMovies(dto.expression).execute()
//            is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId).execute()
//            else -> imdbService.getFullCast((dto as MovieCastRequest).movieId).execute()
//        }
//        val body = response.body()
//        return if (body != null) {
//            body.apply { resultCode = response.code() }
//        } else {
//            Response().apply { resultCode = response.code() }
//        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}