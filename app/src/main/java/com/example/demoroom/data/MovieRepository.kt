package com.example.demoroom.data

import com.example.demoroom.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Repository that fetch movie list from MovieApi.
 */
interface MovieRepository {
    /** Fetches list of movies from MovieApi */
    suspend fun getMovies(): Flow<List<MovieEntity>>
    //suspend fun fetchRemoteMovies()
    suspend fun insertMovies(movie: MovieEntity)

    suspend fun updateMovie(movie: MovieEntity)

}
