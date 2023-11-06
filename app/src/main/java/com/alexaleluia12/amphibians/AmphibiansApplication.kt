package com.alexaleluia12.amphibians

import android.app.Application
import com.alexaleluia12.amphibians.data.AppContainer
import com.alexaleluia12.amphibians.data.DefaultAppContainer

class AmphibiansApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}