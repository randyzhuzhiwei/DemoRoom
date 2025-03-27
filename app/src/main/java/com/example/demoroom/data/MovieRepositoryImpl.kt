package com.example.demoroom.data

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.demoroom.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class MovieRepositoryImpl (
    private val dispatcher: CoroutineDispatcher,
    private val movieDao: MovieDAO
): MovieRepository {
    override suspend fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()


    override suspend fun updateMovie(movie: MovieEntity) {

        try {
            movieDao.update(movie)
        } catch (e: SQLiteConstraintException) {
            Log.e("MovieDAO", "Constraint failure: ${e.message}")
        } catch (e: SQLiteException) {
            Log.e("MovieDAO", "Database error: ${e.message}")
        } catch (e: Exception) {
            Log.e("MovieDAO", "Unexpected error: ${e.message}")
        }

        Log.d("Testing", "updateMovie 4")
    }


    override suspend fun insertMovies(movie: MovieEntity) {
        withContext(dispatcher) {
            movieDao.insertMovie(MovieEntity(
                id=movie.id,
                title = movie.title,
                year = movie.year,
                rated = movie.rated,
                runtime = movie.runtime,
                plot = movie.plot,
                images = movie.images,
                isFavorite = movie.isFavorite
            ))
        }
    }
    }