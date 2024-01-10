package com.katoklizm.myprojectsearchmoviecleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.NetworkClient
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.db.AppDatabase
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie.LocalStorage
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network.IMDbApiService
import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.network.RetrofitNetworkClient
import okhttp3.OkHttpClient
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.SearchHistoryStorage
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.data.local.SharedPreferencesSearchHistoryStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<IMDbApiService> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://imdb-api.com")
            .build()
            .create(IMDbApiService::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

    factory {
        LocalStorage(get())
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
//            .allowMainThreadQueries()
            .build()
    }
}