package com.github.johnondrej.nbaplayers.shared.entities.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiTeam(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val full_name: String,
    val name: String
)
