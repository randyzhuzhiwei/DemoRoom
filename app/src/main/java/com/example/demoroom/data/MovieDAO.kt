package com.example.demoroom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Query("SELECT * FROM Movie")
    fun getMovies(): Flow<List<MovieEntity>>

    @Update
    suspend fun update(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM Movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>


}