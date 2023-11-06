package com.alexaleluia12.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alexaleluia12.amphibians.ui.screen.AmphibiansList
import com.alexaleluia12.amphibians.ui.screen.AmphibiansViewModel
import com.alexaleluia12.amphibians.ui.screen.AppBar

@Composable
fun AmphibiansApp() {

    Scaffold(
        topBar = { AppBar() }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

            AmphibiansList(uiState = viewModel.uiState, onRetry = viewModel::getAmphibians)
        }
    }
}