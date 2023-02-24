package com.github.johnondrej.nbaplayers.features.teamdetail.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnondrej.nbaplayers.features.teamdetail.domain.GetTeamDetailUseCase
import com.github.johnondrej.nbaplayers.shared.presentation.BaseStateViewModel
import com.github.johnondrej.nbaplayers.shared.presentation.UiState
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val teamId: Int,
    private val getTeamDetail: GetTeamDetailUseCase
) : BaseStateViewModel<TeamDetailScreenState>(TeamDetailScreenState()) {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            updateState { state -> state.copy(team = UiState.Loading) }
            try {
                val team = getTeamDetail(teamId)
                updateState { state -> state.copy(team = UiState.Loaded(team)) }
            } catch (e: Exception) {
                updateState { state -> state.copy(team = UiState.Error(e)) }
            }
        }
    }
}
