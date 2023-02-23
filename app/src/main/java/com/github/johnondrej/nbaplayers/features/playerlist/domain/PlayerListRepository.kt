package com.github.johnondrej.nbaplayers.features.playerlist.domain

import androidx.paging.PagingData
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import kotlinx.coroutines.flow.Flow

interface PlayerListRepository {

    fun observePagedPlayerList(): Flow<PagingData<Player>>
}
