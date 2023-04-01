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
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.onearticle.ArticleScreen
import vtsen.hashnode.dev.androidnews.ui.screens.onearticle.OneArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarksScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadScreen
import vtsen.hashnode.dev.androidnews.ui.screens.about.AboutScreen
import vtsen.hashnode.dev.androidnews.ui.screens.onearticle.OneArticleViewModelFactory

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    allArticlesViewModel: AllArticlesViewModel,
    unreadArticlesViewModel: UnreadArticlesViewModel,
    bookmarkArticlesViewModel: BookmarkArticlesViewModel,
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavRoute.Home.path
    ) {
        val navGraphBuilder = this
        addHomeScreen(navHostController, navGraphBuilder, allArticlesViewModel)
        addUnreadScreen(navHostController, navGraphBuilder, unreadArticlesViewModel)
        addBookmarksScreen(navHostController, navGraphBuilder, bookmarkArticlesViewModel)
        addArticleScreen(navHostController, navGraphBuilder)
        addAboutScreen(navHostController, navGraphBuilder)
    }
}

private fun addHomeScreen(
    navHostController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    viewModel: AllArticlesViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.Home.path,
    ) {
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
    viewModel: UnreadArticlesViewModel
) {
    navGraphBuilder.composable(route = NavRoute.Unread.path) {

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
    viewModel: BookmarkArticlesViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.Bookmarks.path,
    ) {

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
            val articlesRepository =
                ArticlesRepositoryImpl.getInstance(LocalContext.current)
            val userPrefsRepository =
                UserPreferencesRepositoryImpl.getInstance(LocalContext.current)

            val viewModel: OneArticleViewModel =
                viewModel(factory = OneArticleViewModelFactory(articlesRepository, userPrefsRepository, id))

            ArticleScreen(viewModel)
        // articleId is null when deep link is https://vtsen.hashnode.dev
        } else {
            navHostController.navigateUp()
        }
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

