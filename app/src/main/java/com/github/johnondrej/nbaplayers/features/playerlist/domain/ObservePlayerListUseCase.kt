package com.github.johnondrej.nbaplayers.features.playerlist.domain

import androidx.paging.PagingData
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import kotlinx.coroutines.flow.Flow

interface ObservePlayerListUseCase {

    operator fun invoke(): Flow<PagingData<Player>>
}

class ObservePlayerListUseCaseImpl(
    private val playerListRepository: PlayerListRepository
) : ObservePlayerListUseCase {

    override fun invoke(): Flow<PagingData<Player>> {
        return playerListRepository.observePagedPlayerList()
    }
}
