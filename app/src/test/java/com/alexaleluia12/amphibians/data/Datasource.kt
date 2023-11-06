package com.alexaleluia12.amphibians.data

import com.alexaleluia12.amphibians.model.Amphibian

object Datasource {
    val amphibians: List<Amphibian> = listOf(
        Amphibian(name = "a", type = "a1", description = "desc a", imgSrc = "fk.igm.pjg"),
        Amphibian(name = "b", type = "b1", description = "desc b", imgSrc = "fj.igm.pjg"),
    )
}
