package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network

import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieCastResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MovieDetailsResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.MoviesSearchResponse
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.dto.NamesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    suspend fun findMovies(@Path("expression") expression: String): MoviesSearchResponse

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    suspend fun getFullCast(@Path("movie_id") movieId: String): MovieCastResponse

    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
    suspend fun searchName(@Path("expression") expression: String): NamesSearchResponse
}