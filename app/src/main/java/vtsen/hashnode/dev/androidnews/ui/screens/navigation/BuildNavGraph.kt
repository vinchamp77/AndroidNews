package vtsen.hashnode.dev.androidnews.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import vtsen.hashnode.dev.androidnews.ui.screens.ArticleScreen
import vtsen.hashnode.dev.androidnews.ui.screens.main.MainScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun BuildNavGraph(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.path
    ) {
        val navGraphBuilder = this
        addMainScreen(navController, navGraphBuilder, viewModel)
        addArticleScreen(navController,navGraphBuilder, viewModel)
    }
}

private fun addMainScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Main.path) {
        MainScreen(
            viewModel,
            navigateToArticle = { id ->
                navController.navigate(NavRoute.Article.withArgs(id.toString()))
            },
        )
    }
}

private fun addArticleScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.Article.withArgsFormat(NavRoute.Article.id),
        arguments = listOf(
            navArgument(NavRoute.Article.id) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        ArticleScreen(
            viewModel,
            id = args?.getInt(NavRoute.Article.id)!!
        )
    }
}
