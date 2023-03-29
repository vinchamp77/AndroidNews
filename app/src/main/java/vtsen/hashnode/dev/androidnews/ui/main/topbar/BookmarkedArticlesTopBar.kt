package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel

@Composable
fun BookmarkedArticlesTopBar(
    navHostController: NavHostController,
    bookmarkArticlesViewModel: BookmarkArticlesViewModel) {

    val searchQuery by bookmarkArticlesViewModel.searchQuery.collectAsStateWithLifecycle()

    ArticlesTopBar(
        navHostController,
        R.string.bookmarked_articles,
        searchQuery,
        bookmarkArticlesViewModel::onSearchQuery)

}