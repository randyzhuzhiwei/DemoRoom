package com.example.demoroom.model

import com.example.demoroom.data.MovieEntity

data class Movie (
    val id: Int,
    val title: String,
    var year: String,
    val rated: String,
    val runtime: String,
    val plot: String,
    val images: List<String>,
    val isFavorite: Boolean
)

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
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