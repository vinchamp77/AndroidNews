package vtsen.hashnode.dev.androidnews.ui.main.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleScreen
import vtsen.hashnode.dev.androidnews.ui.screens.article.OneArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarksScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.searchresults.SearchArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.searchresults.SearchResultsScreen
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadScreen
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.screens.about.AboutScreen
import vtsen.hashnode.dev.androidnews.ui.screens.article.OneArticleViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.screens.searchresults.SearchArticlesViewModelFactory

@Composable
fun NavGraph(modifier: Modifier = Modifier, navHostController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavRoute.Home.path
    ) {
        val navGraphBuilder = this
        addHomeScreen(navHostController, navGraphBuilder)
        addUnreadScreen(navHostController, navGraphBuilder)
        addBookmarksScreen(navHostController, navGraphBuilder)
        addArticleScreen(navHostController, navGraphBuilder)
        addSearchResultsScreen(navHostController, navGraphBuilder)
        addAboutScreen(navHostController, navGraphBuilder)
    }
}

private fun addHomeScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.Home.path,
    ) {
        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: AllArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

        HomeScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id))
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
                navHostController.navigate(NavRoute.Article.withArgs(article.id))
            },
        )
    }
}

private fun addBookmarksScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.Bookmarks.path,
    ) {

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: BookmarkArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

        BookmarksScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id))
            },
        )
    }
}

private fun addArticleScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.Article.withArgsFormat(NavRoute.Article.id),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://vtsen.hashnode.dev/{${NavRoute.Article.id}}"
                action = Intent.ACTION_VIEW
            }
        ),
        arguments = listOf(
            navArgument(NavRoute.Article.id) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->

        val args = navBackStackEntry.arguments

        val id = args?.getString(NavRoute.Article.id)

        if(id != null) {
            val repository =
                ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
            val viewModel: OneArticleViewModel =
                viewModel(factory = OneArticleViewModelFactory(repository, id))

            ArticleScreen(viewModel)
        // articleId is null when deep link is https://vtsen.hashnode.dev
        } else {
            navHostController.navigateUp()
        }
    }
}

private fun addSearchResultsScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.SearchResults.withArgsFormat(
            NavRoute.SearchResults.titleResId, NavRoute.SearchResults.query
        ),
        arguments = listOf(
            navArgument(NavRoute.SearchResults.titleResId) {
                type = NavType.IntType
            },
            navArgument(NavRoute.SearchResults.query) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->

        val args = navBackStackEntry.arguments
        val titleResId = args!!.getInt(NavRoute.SearchResults.titleResId)
        val query = args.getString(NavRoute.SearchResults.query)!!

        val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
        val viewModel: SearchArticlesViewModel = viewModel(
            factory = SearchArticlesViewModelFactory(
                repository, titleResId, query)
        )

        SearchResultsScreen(
            viewModel,
            navigateToArticle = { article ->
                navHostController.navigate(NavRoute.Article.withArgs(article.id))
            },
        )
    }
}

private fun addAboutScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.About.path,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://vtsen.hashnode.dev/about"
                action = Intent.ACTION_VIEW
            }
        ),
    ) { navBackStackEntry ->

        AboutScreen()
    }
}

