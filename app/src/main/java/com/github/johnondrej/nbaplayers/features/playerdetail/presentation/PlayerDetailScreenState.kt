package com.github.johnondrej.nbaplayers.features.playerdetail.presentation

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.presentation.UiState

data class PlayerDetailScreenState(
    val player: UiState<Player> = UiState.Empty
)
