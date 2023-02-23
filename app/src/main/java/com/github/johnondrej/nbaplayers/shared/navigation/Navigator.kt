package com.github.johnondrej.nbaplayers.shared.navigation

import androidx.navigation.NavController

interface Navigator {

    fun navigate(route: Route)
}

class ComposeNavigator(private val navController: NavController) : Navigator {

    override fun navigate(route: Route) {
        navController.navigate(route.routeDefinition)
    }
}
