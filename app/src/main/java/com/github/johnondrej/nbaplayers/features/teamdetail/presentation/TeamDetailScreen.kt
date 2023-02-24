package com.github.johnondrej.nbaplayers.features.teamdetail.presentation

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
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Team
import com.github.johnondrej.nbaplayers.shared.presentation.UiState
import com.github.johnondrej.nbaplayers.shared.presentation.components.DetailParameter
import com.github.johnondrej.nbaplayers.shared.presentation.components.DetailTitle
import com.github.johnondrej.nbaplayers.shared.presentation.components.ErrorMessage
import com.github.johnondrej.nbaplayers.shared.presentation.components.Loader
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TeamDetailScreen(
    teamId: Int,
    viewModel: TeamDetailViewModel = koinViewModel { parametersOf(teamId) }
) {
    when (val teamState = viewModel.uiStateStream.collectAsState().value.team) {
        is UiState.Empty -> {}
        is UiState.Loading -> {
            Loader(modifier = Modifier.fillMaxSize())
        }
        is UiState.Loaded -> {
            TeamDetail(team = teamState.data)
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
private fun TeamDetail(team: Team) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        DetailTitle(
            text = team.fullName,
            modifier = Modifier.fillMaxWidth()
        )

        DetailParameter(
            name = stringResource(id = R.string.team_parameter_abbreviation),
            value = team.abbreviation
        )

        DetailParameter(
            name = stringResource(id = R.string.team_parameter_city),
            value = team.city
        )

        DetailParameter(
            name = stringResource(id = R.string.team_parameter_conference),
            value = team.conference
        )

        DetailParameter(
            name = stringResource(id = R.string.team_parameter_division),
            value = team.division
        )

        DetailParameter(
            name = stringResource(id = R.string.team_parameter_name),
            value = team.name
        )
    }
}
