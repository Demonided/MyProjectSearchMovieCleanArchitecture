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
    fun findMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getFullCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>

    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
    fun searchName(@Path("expression") expression: String): Call<NamesSearchResponse>
}