package com.github.johnondrej.nbaplayers.features.playerdetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.johnondrej.nbaplayers.R
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.presentation.UiState
import com.github.johnondrej.nbaplayers.shared.presentation.components.DetailParameter
import com.github.johnondrej.nbaplayers.shared.presentation.components.DetailTitle
import com.github.johnondrej.nbaplayers.shared.presentation.components.ErrorMessage
import com.github.johnondrej.nbaplayers.shared.presentation.components.Loader
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlayerDetailScreen(
    playerId: Int,
    viewModel: PlayerDetailViewModel = koinViewModel { parametersOf(playerId) }
) {
    when (val playerState = viewModel.uiStateStream.collectAsState().value.player) {
        is UiState.Empty -> {}
        is UiState.Loading -> {
            Loader(modifier = Modifier.fillMaxSize())
        }
        is UiState.Loaded -> {
            PlayerDetail(playerState.data)
        }
        is UiState.Error -> {
            ErrorMessage(
                onRetryClick = { viewModel.fetchData() },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun PlayerDetail(player: Player) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        DetailTitle(
            text = player.fullName,
            modifier = Modifier.fillMaxWidth()
        )

        DetailParameter(
            name = stringResource(id = R.string.player_parameter_position),
            value = player.position
        )

        DetailParameter(
            name = stringResource(id = R.string.player_parameter_height),
            value = player.height
        )

        DetailParameter(
            name = stringResource(id = R.string.player_parameter_weight),
            value = player.weightPounds?.let { weight ->
                stringResource(id = R.string.player_parameter_weight_value, weight)
            }
        )

        DetailParameter(
            name = stringResource(id = R.string.player_parameter_team),
            value = player.team.fullName,
            onClick = {},
        )
    }
}
