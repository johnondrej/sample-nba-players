package com.github.johnondrej.nbaplayers.shared.navigation

import androidx.navigation.NavController

interface Navigator {

    fun navigate(route: Route, argumentValues: List<String> = emptyList())
}

class ComposeNavigator(private val navController: NavController) : Navigator {

    override fun navigate(route: Route, argumentValues: List<String>) {
        navController.navigate(route.resolveRoute(argumentValues = argumentValues))
    }
}
