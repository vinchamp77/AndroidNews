package vtsen.hashnode.dev.androidnews.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.main.topbar.OneArticleTopBar
import vtsen.hashnode.dev.androidnews.ui.main.topbar.BookmarkedArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.main.topbar.AllArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.main.topbar.SearchResultsTopBar
import vtsen.hashnode.dev.androidnews.ui.main.topbar.UnreadArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory

@Composable
fun TopBar(navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route ?: return

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
    val viewModel: ArticlesViewModel = viewModel(factory = ArticlesViewModelFactory(repository))

    // All articles
    if (currentNavRoutePath.contains(NavRoute.Home.path)) {
        AllArticlesTopBar(navHostController)

    // Unread articles
    } else if (currentNavRoutePath.contains(NavRoute.Unread.path)) {
        UnreadArticlesTopBar(navHostController)

    // Bookmarked articles
    } else if (currentNavRoutePath.contains(NavRoute.Bookmarks.path)) {
        BookmarkedArticlesTopBar(navHostController)

    // Search results articles
    } else if (currentNavRoutePath.contains(NavRoute.SearchResults.path)) {
        val args = navBackStackEntry?.arguments
        val titleResId = args?.getInt(NavRoute.SearchResults.titleResId)!!

        SearchResultsTopBar(titleResId)

    // One article
    } else if (currentNavRoutePath.contains(NavRoute.Article.path)) {
        val args = navBackStackEntry?.arguments
        val articleId = args?.getString(NavRoute.Article.id)

        OneArticleTopBar(navHostController, viewModel, articleId!!)

    } else {
        throw Exception("Invalid navigation path!")
    }
}
