package com.alexaleluia12.amphibians.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexaleluia12.amphibians.data.NetworkAmphibiansRepository
import com.alexaleluia12.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.io.IOException

const val TAG = "AmphibiansViewModel"
class AmphibiansViewModel: ViewModel() {
    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    private val url = "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()

    // TODO passar dependecias como parametros
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
    private val amphibiansRepository = NetworkAmphibiansRepository(retrofitService)

    init {
        getAmphibians()
    }

    internal fun getAmphibians() {
        viewModelScope.launch {
            uiState = try {
                val amphibians = amphibiansRepository.getAmphibians()
                AmphibiansUiState.Success(
                    amphibians.slice(0 .. 2)
                )
            } catch (e: IOException) {
                Log.d(TAG, e.toString())
                AmphibiansUiState.Error
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                Log.d(TAG, e.stackTraceToString())
                AmphibiansUiState.Error
            }

        }
    }
}