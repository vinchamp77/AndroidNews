package vtsen.hashnode.dev.androidnews.ui.screens.bookmarks

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.screens.home.ArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeViewModel

@Composable
fun BookmarkedArticlesTopBar(navHostController: NavHostController, viewModel: HomeViewModel) {

    ArticlesTopBar(navHostController, viewModel, viewModel::onBookmarkedArticlesSearch)

}