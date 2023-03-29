package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel

@Composable
fun UnreadArticlesTopBar(
    navHostController: NavHostController,
    unreadArticlesViewModel: UnreadArticlesViewModel) {

    val searchQuery by unreadArticlesViewModel.searchQuery.collectAsStateWithLifecycle()

    ArticlesTopBar(
        navHostController,
        R.string.unread_articles,
        searchQuery,
        unreadArticlesViewModel::onSearchQuery)

}