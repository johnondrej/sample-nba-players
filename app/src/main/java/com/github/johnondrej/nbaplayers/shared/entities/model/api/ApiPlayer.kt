package com.github.johnondrej.nbaplayers.shared.entities.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiPlayer(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val weight_pounds: Int?,
    val team: ApiTeam
)
