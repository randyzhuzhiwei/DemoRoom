package com.example.demoroom.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.demoroom.MovieApplication
import com.example.demoroom.data.MovieEntity
import com.example.demoroom.data.MovieRepository
import com.example.demoroom.data.toMovie
import com.example.demoroom.model.Movie
import com.example.demoroom.model.toMovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    val movies: StateFlow<List<Movie>> get() = _movies

    fun initializeDB()
    {
        viewModelScope.launch {
            movieRepository.getMovies().collect { movieEntities ->
                _movies.value = movieEntities.map { it.toMovie() }
            }
        }
    }

    fun setUpDB() {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.insertMovies(
                MovieEntity(
                    id=1,
                    title = "Inception",
                    year = "2010",
                    rated = "PG-13",
                    runtime = "148 min",
                    plot = "A thief enters the dreams of others to steal secrets.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
                    isFavorite = false
                )

            )
            movieRepository.insertMovies(
                MovieEntity(
                    id=2,
                    title = "300",
                    year = "2006",
                    rated = "R",
                    runtime = "117 min",
                    plot = "King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg"),
                    isFavorite = false
                )

            )
            movieRepository.insertMovies(
                MovieEntity(
                    id=3,
                    title = "The Avengers",
                    year = "2012",
                    rated = "PG-13",
                    runtime = "143 min",
                    plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"),
                    isFavorite = false
                )

            )
            movieRepository.insertMovies(
                MovieEntity(
                    id=4,
                    title = "The Wolf of Wall Street",
                    year = "2013",
                    rated = "R",
                    runtime = "180 min",
                    plot = "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BNDIwMDIxNzk3Ml5BMl5BanBnXkFtZTgwMTg0MzQ4MDE@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
                    isFavorite = false
                )

            )
            movieRepository.insertMovies(
                MovieEntity(
                    id=5,
                    title = "Interstellar",
                    year = "2014",
                    rated = "PG-13",
                    runtime = "169 min",
                    plot = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjA3NTEwOTMxMV5BMl5BanBnXkFtZTgwMjMyODgxMzE@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
                    isFavorite = false
                )

            )
        }
    }
    fun updateMovie(movie: Movie) {
        viewModelScope.launch {
            movieRepository.updateMovie(movie.toMovieEntity())
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieApplication)

                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository = movieRepository)
            }
        }
    }
}
