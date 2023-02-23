package com.github.johnondrej.nbaplayers.features.playerlist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.github.johnondrej.nbaplayers.R
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiStateStream.collectAsState()
    val players = uiState.players.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        }
    ) { paddingValues ->
        PlayersList(
            players = players,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun PlayersList(
    players: LazyPagingItems<Player>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(players, key = { it.id }) { player ->
            player?.let { playerItem ->
                PlayerItem(
                    player = playerItem,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun PlayerItem(
    player: Player,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val playerPositionSuffix = player.position?.let { " (${player.position})" } ?: ""
        val playerTitle = "${player.fullName}$playerPositionSuffix"

        Text(text = playerTitle, style = MaterialTheme.typography.subtitle2)

        Text(
            text = player.team.fullName,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.alpha(0.7f)
        )
    }
}
