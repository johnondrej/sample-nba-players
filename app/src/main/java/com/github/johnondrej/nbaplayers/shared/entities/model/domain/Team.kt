package com.github.johnondrej.nbaplayers.shared.entities.model.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiTeam

data class Team(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String
)

fun ApiTeam.toDomain(): Team = Team(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = full_name,
    name = name
)
