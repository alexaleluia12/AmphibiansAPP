package com.alexaleluia12.amphibians.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alexaleluia12.amphibians.AmphibiansApplication
import com.alexaleluia12.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch
import java.io.IOException

const val TAG = "AmphibiansViewModel"

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    internal fun getAmphibians() {
        viewModelScope.launch {
            uiState = try {
                val amphibians = amphibiansRepository.getAmphibians()
                AmphibiansUiState.Success(amphibians)
            } catch (e: IOException) {
                Log.d(TAG, e.toString())
                AmphibiansUiState.Error
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                AmphibiansUiState.Error
            }
        }
    }

    // necessario um construtor separado
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                // dependencia camada 2, dependencia de rempository foi definida emm AppContainer
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository)
            }
        }
    }
}