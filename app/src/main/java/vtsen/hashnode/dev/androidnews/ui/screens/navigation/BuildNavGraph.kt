package vtsen.hashnode.dev.androidnews.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import vtsen.hashnode.dev.androidnews.ui.screens.ArticleScreen
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarksScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeScreen
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun BuildNavGraph(viewModel: MainViewModel, navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = NavRoute.Home.path
    ) {
        val navGraphBuilder = this
        addHomeScreen(navHostController, navGraphBuilder, viewModel)
        addUnreadScreen(navHostController, navGraphBuilder, viewModel)
        addBookmarksScreen(navHostController, navGraphBuilder, viewModel)
        addArticleScreen(navHostController,navGraphBuilder, viewModel)
    }
}

private fun addHomeScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(
            viewModel,
            navigateToArticle = { id ->
                navHostController.navigate(NavRoute.Article.withArgs(id.toString()))
            },
        )
    }
}

private fun addUnreadScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Unread.path) {
        UnreadScreen(
            viewModel,
            navigateToArticle = { id ->
                navHostController.navigate(NavRoute.Article.withArgs(id.toString()))
            },
        )
    }
}

private fun addBookmarksScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Bookmarks.path) {
        BookmarksScreen(
            viewModel,
            navigateToArticle = { id ->
                navHostController.navigate(NavRoute.Article.withArgs(id.toString()))
            },
        )
    }
}

private fun addArticleScreen(
    navHostController: NavHostController,
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
