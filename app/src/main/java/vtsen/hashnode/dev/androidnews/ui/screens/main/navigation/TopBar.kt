package vtsen.hashnode.dev.androidnews.ui.screens.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkedArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.home.SearchResultsTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun TopBar(navHostController: NavHostController, viewModel: MainViewModel) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route ?: return

    // All articles
    if (currentNavRoutePath.contains(NavRoute.Home.path)) {
        AllArticlesTopBar(navHostController, viewModel)
    // Unread articles
    } else if (currentNavRoutePath.contains(NavRoute.Unread.path)) {
        UnreadArticlesTopBar(navHostController, viewModel)
    // Bookmarked articles
    } else if (currentNavRoutePath.contains(NavRoute.Bookmarks.path)) {
        BookmarkedArticlesTopBar(navHostController, viewModel)
    // Search results articles
    } else if (currentNavRoutePath.contains(NavRoute.SearchResults.path)) {
        SearchResultsTopBar(navHostController, viewModel)
    // Single article
    } else if (currentNavRoutePath.contains(NavRoute.Article.path)) {
        val args = navBackStackEntry?.arguments
        val id = args?.getInt(NavRoute.Article.id)
        ArticleTopBar(navHostController, viewModel)
    } else {
        throw Exception("Invalid navigation path!")
    }
}
