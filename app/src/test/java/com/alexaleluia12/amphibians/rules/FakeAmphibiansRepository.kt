package com.alexaleluia12.amphibians.rules

import com.alexaleluia12.amphibians.data.AmphibiansRepository
import com.alexaleluia12.amphibians.data.Datasource
import com.alexaleluia12.amphibians.model.Amphibian
import java.io.IOException

class FakeAmphibiansRepository: AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return Datasource.amphibians
    }
}

class FakeAmphibiansRepositoryFailed: AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        throw IOException("fail fake for test")
    }
}