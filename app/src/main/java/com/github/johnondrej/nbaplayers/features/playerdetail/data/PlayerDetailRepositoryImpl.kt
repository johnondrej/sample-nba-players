package com.github.johnondrej.nbaplayers.features.playerdetail.data

import com.github.johnondrej.nbaplayers.features.playerdetail.domain.PlayerDetailRepository
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.toDomain
import com.github.johnondrej.nbaplayers.shared.networking.ApiService

class PlayerDetailRepositoryImpl(private val apiService: ApiService) : PlayerDetailRepository {

    override suspend fun getPlayer(playerId: Int): Player {
        return apiService.getPlayer(playerId).toDomain()
    }
}
