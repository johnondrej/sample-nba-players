package com.github.johnondrej.nbaplayers.features.teamdetail.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Team

interface GetTeamDetailUseCase {

    suspend operator fun invoke(teamId: Int): Team
}

class GetTeamDetailUseCaseImpl(
    private val teamDetailRepository: TeamDetailRepository
) : GetTeamDetailUseCase {

    override suspend fun invoke(teamId: Int): Team {
        return teamDetailRepository.getTeam(teamId)
    }
}
