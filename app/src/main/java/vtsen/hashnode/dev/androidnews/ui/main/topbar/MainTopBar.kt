package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.onearticle.OneArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.onearticle.OneArticleViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel

@Composable
fun TopBar(
    navHostController: NavHostController,
    allArticlesViewModel: AllArticlesViewModel,
    unreadArticlesViewModel: UnreadArticlesViewModel,
    bookmarkArticlesViewModel: BookmarkArticlesViewModel
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route ?: return

    val articlesRepository = ArticlesRepositoryImpl.getInstance(LocalContext.current.applicationContext)
    val userPrefsRepository =
        UserPreferencesRepositoryImpl.getInstance(LocalContext.current)

    // All articles
    if (currentNavRoutePath.contains(NavRoute.Home.path)) {
        AllArticlesTopBar(navHostController, allArticlesViewModel)

    // Unread articles
    } else if (currentNavRoutePath.contains(NavRoute.Unread.path)) {
        UnreadArticlesTopBar(navHostController, unreadArticlesViewModel)

    // Bookmarked articles
    } else if (currentNavRoutePath.contains(NavRoute.Bookmarks.path)) {
        BookmarkedArticlesTopBar(navHostController, bookmarkArticlesViewModel)

    // Search results articles
    } else if (currentNavRoutePath.contains(NavRoute.SearchResults.path)) {
        val args = navBackStackEntry?.arguments
        val titleResId = args?.getInt(NavRoute.SearchResults.titleResId)!!

        SearchResultsTopBar(titleResId)

    // One article
    } else if (currentNavRoutePath.contains(NavRoute.Article.path)) {
        val args = navBackStackEntry?.arguments
        val articleId = args?.getString(NavRoute.Article.id)

        // articleId is null when deep link is https://vtsen.hashnode.dev
        // we navigate back to the home screen. See NavGraph.kt
        if(articleId != null) {

            val viewModel: OneArticleViewModel =
                viewModel(factory = OneArticleViewModelFactory(
                    articlesRepository, userPrefsRepository, articleId))
            OneArticleTopBar(navHostController, viewModel)
        }

    } else if (currentNavRoutePath.contains(NavRoute.About.path)) {
        AboutTopBar(navHostController)

    } else {
        throw Exception("Invalid navigation path!")
    }
}
