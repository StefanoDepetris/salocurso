package com.example.salo.repository

import com.example.salo.application.AppConstance
import com.example.salo.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//Encargado de usar retrofit para traer la informacion del servidor.

//Ya puedo en mi respositorio llamar a los metodos de datasource y desde ahi  usar el webservice para consultar datos al servidor.

interface WebService{

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(@Query ("api_key") api_key:String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRateMovies(@Query ("api_key") api_key:String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query ("api_key") api_key:String): MovieList



}

object RetrofitClient{
    //el by lazy cuando llamemos retofitClient.webservice se va inicilizar cunado se llame no antes
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}