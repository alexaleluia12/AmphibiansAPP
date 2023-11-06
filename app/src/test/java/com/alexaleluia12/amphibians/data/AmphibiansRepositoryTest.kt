package com.alexaleluia12.amphibians.data

import com.alexaleluia12.amphibians.fake.FakeApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class AmphibiansRepositoryTest {
    val repository: AmphibiansRepository = NetworkAmphibiansRepository(FakeApiService())

    @Test
    fun getAmphibians_successResult() = runTest {
        val result = repository.getAmphibians()
        Assert.assertEquals(2 , result.size)
    }
}