package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun AllArticlesTopBar(navHostController: NavHostController, viewModel: MainViewModel) {

    ArticlesTopBar(navHostController, viewModel, viewModel::onAllArticlesSearch)

}