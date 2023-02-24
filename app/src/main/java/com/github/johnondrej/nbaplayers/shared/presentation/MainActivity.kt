package com.github.johnondrej.nbaplayers.shared.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.johnondrej.nbaplayers.features.playerdetail.presentation.PlayerDetailScreen
import com.github.johnondrej.nbaplayers.features.playerlist.presentation.PlayerListScreen
import com.github.johnondrej.nbaplayers.features.teamdetail.presentation.TeamDetailScreen
import com.github.johnondrej.nbaplayers.shared.navigation.ComposeNavigator
import com.github.johnondrej.nbaplayers.shared.navigation.Navigator
import com.github.johnondrej.nbaplayers.shared.navigation.Route
import com.github.johnondrej.nbaplayers.shared.presentation.theme.NBAPlayersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBAPlayersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navigator: Navigator = remember { ComposeNavigator(navController) }

                    NavHost(
                        navController = navController,
                        startDestination = Route.PLAYER_LIST.routeDefinition
                    ) {
                        composable(route = Route.PLAYER_LIST.routeDefinition) {
                            PlayerListScreen(
                                onPlayerClick = { player ->
                                    navigator.navigate(
                                        route = Route.PLAYER_DETAIL,
                                        argumentValues = listOf(player.id.toString())
                                    )
                                }
                            )
                        }

                        composable(
                            route = Route.PLAYER_DETAIL.routeDefinition
                        ) { backStackEntry ->
                            backStackEntry.arguments
                                ?.getString(Route.PLAYER_DETAIL.arguments.first())
                                ?.toInt()
                                ?.let { playerId ->
                                    PlayerDetailScreen(
                                        playerId = playerId,
                                        onTeamClick = { team ->
                                            navigator.navigate(
                                                route = Route.TEAM_DETAIL,
                                                argumentValues = listOf(team.id.toString())
                                            )
                                        }
                                    )
                                }
                        }

                        composable(
                            route = Route.TEAM_DETAIL.routeDefinition
                        ) { backStackEntry ->
                            backStackEntry.arguments
                                ?.getString(Route.TEAM_DETAIL.arguments.first())
                                ?.toInt()
                                ?.let { teamId ->
                                    TeamDetailScreen(teamId = teamId)
                                }
                        }
                    }
                }
            }
        }
    }
}
