package com.github.johnondrej.nbaplayers.features.playerlist.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.johnondrej.nbaplayers.features.playerlist.domain.PlayerListRepository
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.presentation.BaseStateViewModel
import kotlinx.coroutines.flow.Flow

class PlayerListViewModel(
    private val playerListRepository: PlayerListRepository
) : BaseStateViewModel<PlayerListScreenState>(PlayerListScreenState()) {

    init {
        updateState { state ->
            state.copy(players = observePlayerList())
        }
    }

    private fun observePlayerList(): Flow<PagingData<Player>> {
        return playerListRepository
            .observePagedPlayerList()
            .cachedIn(viewModelScope)
    }
}
