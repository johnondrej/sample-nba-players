package com.github.johnondrej.nbaplayers.features.playerlist.presentation

import androidx.paging.PagingData
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PlayerListScreenState(
    val players: Flow<PagingData<Player>> = emptyFlow()
)
