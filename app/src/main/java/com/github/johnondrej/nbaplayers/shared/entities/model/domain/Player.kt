package com.github.johnondrej.nbaplayers.shared.entities.model.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiPlayer

data class Player(
    val id: Int,
    val fullName: String,
    val position: String?,
    val height: String?,
    val weightPounds: Int?,
    val team: Team
)

fun ApiPlayer.toDomain(): Player = Player(
    id = id,
    fullName = "$first_name $last_name",
    position = position.takeIf { it.isNotBlank() },
    height = if (height_feet != null && height_inches != null) {
        "${height_feet}' ${height_inches}''"
    } else {
        null
    },
    weightPounds = weight_pounds,
    team = team.toDomain()
)
