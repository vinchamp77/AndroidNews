package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.screens.home.ArticlesTopBar
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun UnreadArticlesTopBar(navHostController: NavHostController, viewModel: MainViewModel) {

    ArticlesTopBar(navHostController, viewModel, viewModel::onUnreadArticlesSearch)

}