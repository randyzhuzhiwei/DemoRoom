package com.example.demoroom.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MovieEntity::class],
    version = 2,
)
@TypeConverters(MovieTypeConverters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}