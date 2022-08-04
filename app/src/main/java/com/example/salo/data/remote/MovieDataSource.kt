package com.example.salo.data.remote

import com.example.salo.application.AppConstance
import com.example.salo.data.model.MovieList
import com.example.salo.repository.WebService


class MovieDataSource(val webService: WebService) {
    //Traer 3 listas aca van a estar los metodos que traen los datos.Este DataSource es llamado del Repositorio pero necesita ir a buscar la informacion al webservice
    suspend fun getUpComingMovies(): MovieList = webService.getUpComingMovies(AppConstance.api_key)
    suspend fun getTopRateMovies(): MovieList = webService.getTopRateMovies(AppConstance.api_key)
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstance.api_key)
}