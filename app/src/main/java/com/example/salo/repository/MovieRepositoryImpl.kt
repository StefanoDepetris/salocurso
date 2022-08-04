package com.example.salo.repository

import com.example.salo.data.model.MovieList
import com.example.salo.data.remote.MovieDataSource

//Aca van estar las interfaces web service y  lo que implemente las itnerfaces.ACA VA LA FIRMA DE LOS METODOS Y LA INTERPRETACION DE DONDE BUSCAR LA INFORMACION.NO HAY LOGICA DE NEGOCIO
//INTERFAZ CON METODOS QUE VAMOS USAR PARA BUSCAR INFORMACION AL  SERVER Y  UNA CLASE QUE  HAGA ESTOS METODOS

//Llamamos a los metodos de MovieDataSource
class MovieRepositoryImpl(private val dataSource: MovieDataSource) :MovieRepository {
    override suspend fun getUpComingMovies(): MovieList=dataSource.getUpComingMovies()
    override suspend fun getTopRateMovies(): MovieList= dataSource.getTopRateMovies()
    override suspend fun getPopularMovies(): MovieList=dataSource.getPopularMovies()
}