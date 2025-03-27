package com.example.demoroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.demoroom.model.Movie

@Entity(tableName = "Movie")
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val year: String,
    val rated: String,
    val runtime: String,
    val plot: String,
    val images: List<String>,
    val isFavorite: Boolean
)
fun MovieEntity.toMovie(): Movie {
    return Movie(
        id=this.id,
        title = this.title,
        year = this.year,
        rated = this.rated,
        runtime = this.runtime,
        plot = this.plot,
        images = this.images,
        isFavorite = this.isFavorite
    )
}