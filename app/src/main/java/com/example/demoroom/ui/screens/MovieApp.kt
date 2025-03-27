package com.example.demoroom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.demoroom.R
import com.example.demoroom.data.MovieEntity
import com.example.demoroom.model.Movie
import com.example.demoroom.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun MovieApp() {
    val sharedMovieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
    MovieList(movies = sharedMovieViewModel.movies, onMovieClick = {},sharedMovieViewModel)

}

// UI Composable to Display Movies
@Composable
fun MovieList(movies: StateFlow<List<Movie>>, onMovieClick: (Movie) -> Unit,sharedMovieViewModel: MovieViewModel) {

    val movieList by movies.collectAsState(initial = emptyList())


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Buttons Row
        LazyColumn {
            items(movieList) { movie ->
                MovieItem(movie = movie, onClick = { onMovieClick(movie) })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                var newUpdatedMovie:Movie = movieList.get(0)
                newUpdatedMovie.year="1000"
                sharedMovieViewModel.updateMovie(newUpdatedMovie) }) {
                Text("Update Movie")
            }
            Button(onClick = {  }) {
                Text("Query Movies")
            }
        }
    }


}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() },
        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(movie.images[0])
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_connection_error),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.title, style = MaterialTheme.typography.displayLarge)
            Text(text = "Year: ${movie.year}")
            Text(text = "Rated: ${movie.rated}")
            Text(text = "Runtime: ${movie.runtime}")
            Text(text = movie.plot, maxLines = 3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieList() {
    val sampleMovies = listOf(
        MovieEntity(
            id = 123,
            title = "Inception",
            year = "2010",
            rated = "PG-13",
            runtime = "148 min",
            plot = "A thief who enters the dreams of others to steal secrets.",
            images = listOf("https://example.com/inception.jpg"),
            isFavorite = false
        )
    )
    //MovieList(movies = sampleMovies, onMovieClick = {})
}
