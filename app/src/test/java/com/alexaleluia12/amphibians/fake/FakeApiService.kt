package com.alexaleluia12.amphibians.fake

import com.alexaleluia12.amphibians.data.Datasource
import com.alexaleluia12.amphibians.model.Amphibian
import com.alexaleluia12.amphibians.network.AmphibiansApiService


class FakeApiService: AmphibiansApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return Datasource.amphibians
    }
}