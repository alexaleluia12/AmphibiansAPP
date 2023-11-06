package com.alexaleluia12.amphibians.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.alexaleluia12.amphibians.network.AmphibiansApiService
import com.alexaleluia12.amphibians.ui.screen.AmphibiansUiState
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/*
 objeto para guardar as referencias de dependicia usado entre os componentes
 repository/apiService
 */
interface AppContainer {
    // depenencias de camada 1
    val amphibiansRepository: AmphibiansRepository
}

class DefaultAppContainer: AppContainer {

    private val url = "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitService)
    }

}