package com.example.ecommerceapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecommerceapp.ui.detail.DetailScreen
import com.example.ecommerceapp.ui.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{productId}") {
        fun createRoute(productId: Int) = "detail/$productId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.Detail.createRoute(productId))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            DetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
