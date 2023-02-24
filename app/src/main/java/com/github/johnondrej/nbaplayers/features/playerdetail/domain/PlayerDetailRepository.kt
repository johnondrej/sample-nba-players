package com.github.johnondrej.nbaplayers.features.playerdetail.domain

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player

interface PlayerDetailRepository {

    suspend fun getPlayer(playerId: Int): Player
}
