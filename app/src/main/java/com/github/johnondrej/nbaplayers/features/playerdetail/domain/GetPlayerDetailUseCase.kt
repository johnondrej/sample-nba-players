package com.github.johnondrej.nbaplayers.features.playerdetail.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player

interface GetPlayerDetailUseCase {

    suspend operator fun invoke(playerId: Int): Player
}

class GetPlayerDetailUseCaseImpl(
    private val playerDetailRepository: PlayerDetailRepository
) : GetPlayerDetailUseCase {

    override suspend fun invoke(playerId: Int): Player {
        return playerDetailRepository.getPlayer(playerId)
    }
}
