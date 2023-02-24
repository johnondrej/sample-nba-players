package com.github.johnondrej.nbaplayers.features.teamdetail.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Team

interface TeamDetailRepository {

    suspend fun getTeam(teamId: Int): Team
}
