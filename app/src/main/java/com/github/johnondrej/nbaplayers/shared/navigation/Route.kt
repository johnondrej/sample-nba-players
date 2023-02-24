package com.github.johnondrej.nbaplayers.shared.navigation

enum class Route(
    private val routeName: String,
    val arguments: List<String> = emptyList(),
) {

    PLAYER_LIST(routeName = "playerList"),
    PLAYER_DETAIL(routeName = "playerDetail", arguments = listOf("playerId")),
    TEAM_DETAIL(routeName = "teamDetail", arguments = listOf("teamId"));

    val routeDefinition: String
        get() {
            val argumentsDefinition = if (arguments.isNotEmpty()) {
                arguments.joinToString(separator = "", prefix = "/{", postfix = "}")
            } else {
                ""
            }

            return "${routeName}$argumentsDefinition"
        }

    fun resolveRoute(argumentValues: List<String> = emptyList()): String {
        return "${routeName}${argumentValues.joinToString(separator = "", prefix = "/")}"
    }
}
