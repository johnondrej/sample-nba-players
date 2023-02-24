package com.github.johnondrej.nbaplayers.features.playerlist.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.johnondrej.nbaplayers.features.playerlist.domain.ObservePlayerListUseCase
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.presentation.BaseStateViewModel
import kotlinx.coroutines.flow.Flow

class PlayerListViewModel(
    private val observePlayerList: ObservePlayerListUseCase
) : BaseStateViewModel<PlayerListScreenState>(PlayerListScreenState()) {

    init {
        updateState { state ->
            state.copy(players = observePlayers())
        }
    }

    private fun observePlayers(): Flow<PagingData<Player>> {
        return observePlayerList().cachedIn(viewModelScope)
    }
}
