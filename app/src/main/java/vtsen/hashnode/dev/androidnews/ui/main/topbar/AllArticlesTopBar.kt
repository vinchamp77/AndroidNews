package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel

@Composable
fun AllArticlesTopBar(
    navHostController: NavHostController,
    allArticlesViewModel: AllArticlesViewModel,
    showReviewDialog: () -> Unit,
) {

    val searchQuery by allArticlesViewModel.searchQuery.collectAsStateWithLifecycle()

    ArticlesTopBar(
        navHostController,
        searchQuery,
        allArticlesViewModel::onSearchQuery,
        showReviewDialog
    )

}