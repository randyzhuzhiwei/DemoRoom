package com.example.demoroom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demoroom.ui.screens.MovieApp
import com.example.demoroom.ui.theme.DemoRoomTheme
import com.example.demoroom.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

import androidx.lifecycle.viewmodel.compose.viewModel
import leakcanary.AppWatcher

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoRoomTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val isInitialized = remember { mutableStateOf(false) }
                    val coroutineScope = rememberCoroutineScope()
                    val sharedMovieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)

                    // Initialize DB
                    LaunchedEffect(Unit) {
                        coroutineScope.launch {
                            sharedMovieViewModel.setUpDB()
                            sharedMovieViewModel.initializeDB()
                            isInitialized.value = true // Mark as initialized
                        }
                    }

                    // Show a loading screen until data is ready
                    if (isInitialized.value) {
                        Log.d("Logging","Starting App")
                        AppWatcher.objectWatcher.expectWeaklyReachable(LeakTestUtils.leakCanaryTest,"Static reference to LeakCanaryTest")
                        MovieApp()
                    } else {
                        LoadingScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Loading movies...")
        }
    }
}