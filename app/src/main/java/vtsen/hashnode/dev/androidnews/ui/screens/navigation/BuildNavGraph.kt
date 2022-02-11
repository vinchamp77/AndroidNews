package com.example.simplenavigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.ui.screens.MainScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun BuildNavGraph(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.path
    ) {
        addMainScreen(navController, this, viewModel)
    }
}

private fun addMainScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Main.path) {
        MainScreen(viewModel)
//            navigateToHome = {
//                navController.navigate(NavRoute.Home.path)
//            }
//        )
    }
}

//private fun addHomeScreen(
//    navController: NavHostController,
//    navGraphBuilder: NavGraphBuilder
//) {
//    navGraphBuilder.composable(route = NavRoute.Home.path) {
//
//        HomeScreen(
//            navigateToProfile = { id, showDetails ->
//                navController.navigate(NavRoute.Profile.withArgs(id.toString(), showDetails.toString()))
//            },
//            navigateToSearch = { query ->
//                navController.navigate(NavRoute.Search.withArgs(query))
//            },
//            popBackStack = { navController.popBackStack() },
//            popUpToLogin= { popUpToLogin(navController) },
//        )
//    }
//}
//
//private fun popUpToLogin(navController: NavHostController) {
//    navController.navigate(NavRoute.Login.path) {
//        popUpTo(NavRoute.Login.path) {inclusive = true}
//    }
//}
//
//private fun addProfileScreen(
//    navController: NavHostController,
//    navGraphBuilder: NavGraphBuilder
//) {
//    navGraphBuilder.composable(
//        route = NavRoute.Profile.withArgsFormat(NavRoute.Profile.id, NavRoute.Profile.showDetails),
//        arguments = listOf(
//            navArgument(NavRoute.Profile.id) {
//                type = NavType.IntType
//            }
//            ,
//            navArgument(NavRoute.Profile.showDetails) {
//                type = NavType.BoolType
//                defaultValue = false
//            }
//        )
//    ) { navBackStackEntry ->
//
//        val args = navBackStackEntry.arguments
//
//        ProfileScreen(
//            id = args?.getInt(NavRoute.Profile.id)!!,
//            showDetails = args.getBoolean(NavRoute.Profile.showDetails),
//            popBackStack = { navController.popBackStack() },
//            popUpToLogin = { popUpToLogin(navController) }
//        )
//    }
//}
//
//private fun addSearchScreen(
//    navController: NavHostController,
//    navGraphBuilder: NavGraphBuilder
//) {
//    navGraphBuilder.composable(
//        route = NavRoute.Search.withArgsFormat(NavRoute.Search.query),
//        arguments = listOf(
//            navArgument(NavRoute.Search.query) {
//                type = NavType.StringType
//                nullable = true
//            }
//        )
//    ) { navBackStackEntry ->
//
//        val args = navBackStackEntry.arguments
//
//        SearchScreen(
//            query = args?.getString(NavRoute.Search.query),
//            popBackStack = { navController.popBackStack() },
//            popUpToLogin = { popUpToLogin(navController) }
//        )
//    }
//}
