package com.example.demoroom

import android.app.Application
import com.example.demoroom.data.AppContainer

class MovieApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
