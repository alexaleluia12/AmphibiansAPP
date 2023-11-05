package com.alexaleluia12.amphibians.ui.screen

import com.alexaleluia12.amphibians.model.Amphibian

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}
