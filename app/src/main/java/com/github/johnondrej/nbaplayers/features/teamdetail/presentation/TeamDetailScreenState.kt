package com.github.johnondrej.nbaplayers.features.teamdetail.presentation

import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Team
import com.github.johnondrej.nbaplayers.shared.presentation.UiState

data class TeamDetailScreenState(
    val team: UiState<Team> = UiState.Empty
)
