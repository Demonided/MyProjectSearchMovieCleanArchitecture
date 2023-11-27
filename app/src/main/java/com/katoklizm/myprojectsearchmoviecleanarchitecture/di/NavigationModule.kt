//package com.katoklizm.myprojectsearchmoviecleanarchitecture.di
//
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.Router
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.RouterImpl
//import org.koin.dsl.module
//
//val navigationModule = module {
//    val router = RouterImpl()
//
//    single<Router> { router }
//    single { router.navigatorHolder }
//}