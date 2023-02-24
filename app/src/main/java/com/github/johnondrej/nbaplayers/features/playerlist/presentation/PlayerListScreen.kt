package com.github.johnondrej.nbaplayers.features.playerlist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.github.johnondrej.nbaplayers.R
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel = koinViewModel(),
    onPlayerClick: (Player) -> Unit
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
            onPlayerClick = onPlayerClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun PlayersList(
    players: LazyPagingItems<Player>,
    onPlayerClick: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(players, key = { it.id }) { player ->
            player?.let { playerItem ->
                PlayerItem(
                    player = playerItem,
                    onClick = { onPlayerClick(playerItem) }
                )
            }
        }

        if (players.loadState.isLoading) {
            item {
                LoadingItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }

        if (players.loadState.isError) {
            item {
                ErrorItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { players.retry() }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
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

@Composable
private fun LoadingItem(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun ErrorItem(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.player_list_error_retry),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

private val CombinedLoadStates.isLoading: Boolean
    get() = append == LoadState.Loading || refresh == LoadState.Loading

private val CombinedLoadStates.isError: Boolean
    get() = append is LoadState.Error || refresh is LoadState.Error
