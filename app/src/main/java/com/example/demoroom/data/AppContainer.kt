package com.example.demoroom.data

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers

// Dependency Injection Container
class AppContainer(application: Application) {
    private val database: MovieDatabase = Room.databaseBuilder(
        application,
        MovieDatabase::class.java,
        "movie_database"
    ).fallbackToDestructiveMigration()
        .build()

    private val movieDao: MovieDAO = database.movieDao()
    val movieRepository: MovieRepository = MovieRepositoryImpl(Dispatchers.IO,movieDao)

}
