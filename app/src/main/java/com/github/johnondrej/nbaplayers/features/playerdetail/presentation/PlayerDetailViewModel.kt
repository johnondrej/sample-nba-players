package com.github.johnondrej.nbaplayers.features.playerdetail.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnondrej.nbaplayers.features.playerdetail.domain.GetPlayerDetailUseCase
import com.github.johnondrej.nbaplayers.shared.presentation.BaseStateViewModel
import com.github.johnondrej.nbaplayers.shared.presentation.UiState
import kotlinx.coroutines.launch

class PlayerDetailViewModel(
    private val playerId: Int,
    private val getPlayerDetail: GetPlayerDetailUseCase
) : BaseStateViewModel<PlayerDetailScreenState>(PlayerDetailScreenState()) {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            updateState { state -> state.copy(player = UiState.Loading) }
            try {
                val player = getPlayerDetail(playerId)
                updateState { state -> state.copy(player = UiState.Loaded(player)) }
            } catch (e: Exception) {
                updateState { state -> state.copy(player = UiState.Error(e)) }
            }
        }
    }
}
