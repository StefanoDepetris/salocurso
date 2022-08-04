package com.example.salo.repository

import com.example.salo.data.model.MovieList

interface MovieRepository {

    suspend fun getUpComingMovies(): MovieList
    suspend fun getTopRateMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}