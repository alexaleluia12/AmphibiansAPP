package com.alexaleluia12.amphibians.ui.screen

import com.alexaleluia12.amphibians.data.Datasource
import com.alexaleluia12.amphibians.rules.FakeAmphibiansRepository
import com.alexaleluia12.amphibians.rules.FakeAmphibiansRepositoryFailed
import com.alexaleluia12.amphibians.rules.TestDispatcherRule
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AmphibiansViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getAmphibians_loadSuccess() = runTest {
        val amphibiansViewModel = AmphibiansViewModel(FakeAmphibiansRepository())

        Assert.assertEquals(
            AmphibiansUiState.Success(Datasource.amphibians),
            amphibiansViewModel.uiState
        )
    }

    @Test
    fun getAmphibians_loadFail() = runTest {
        val amphibiansViewModel = AmphibiansViewModel(FakeAmphibiansRepositoryFailed())

        Assert.assertEquals(
            AmphibiansUiState.Error,
            amphibiansViewModel.uiState
        )
    }
}