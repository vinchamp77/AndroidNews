package vtsen.hashnode.dev.androidnews.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.ArticleScreen
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarksScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.searchresults.SearchResultsScreen
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadScreen
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticleViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModelFactory

@Composable
fun NavGraph(viewModel: MainViewModel, navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = NavRoute.Home.path
    ) {
        val navGraphBuilder = this
        addHomeScreen(navHostController, navGraphBuilder)
        addUnreadScreen(navHostController, navGraphBuilder)
        addBookmarksScreen(navHostController, navGraphBuilder)
        addArticleScreen(navGraphBuilder)
        addSearchResultsScreen(navHostController,navGraphBuilder, viewModel)
    }
}

private fun addHomeScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: AllArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

        HomeScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id.toString()))
            },
        )
    }
}

private fun addUnreadScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = NavRoute.Unread.path) {

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: UnreadArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

        UnreadScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id.toString()))
            },
        )
    }
}

private fun addBookmarksScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = NavRoute.Bookmarks.path) {

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: BookmarkArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

        BookmarksScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id.toString()))
            },
        )
    }
}

private fun addArticleScreen(
    navGraphBuilder: NavGraphBuilder,
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
        val id = args?.getInt(NavRoute.Article.id)!!

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: ArticleViewModel = viewModel(factory = ArticleViewModelFactory(repository, id))

        ArticleScreen(viewModel)
    }
}

private fun addSearchResultsScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: MainViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.SearchResults.path
    ) {

        SearchResultsScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id.toString()))
            },
        )
    }
}
