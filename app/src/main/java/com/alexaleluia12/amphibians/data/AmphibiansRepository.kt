package com.alexaleluia12.amphibians.data

import com.alexaleluia12.amphibians.model.Amphibian
import com.alexaleluia12.amphibians.network.AmphibiansApiService


interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(private val apiAmphibians: AmphibiansApiService) :
    AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return apiAmphibians.getAmphibians()
    }
}