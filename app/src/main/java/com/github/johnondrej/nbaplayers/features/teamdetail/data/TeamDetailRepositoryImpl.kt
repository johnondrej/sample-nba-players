package com.github.johnondrej.nbaplayers.features.teamdetail.data

import com.github.johnondrej.nbaplayers.features.teamdetail.domain.TeamDetailRepository
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Team
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.toDomain
import com.github.johnondrej.nbaplayers.shared.networking.ApiService

class TeamDetailRepositoryImpl(private val apiService: ApiService) : TeamDetailRepository {

    override suspend fun getTeam(teamId: Int): Team {
        return apiService.getTeam(teamId).toDomain()
    }
}
