package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AllArticlesTopBar(navHostController: NavHostController, viewModel: HomeViewModel) {

    ArticlesTopBar(navHostController, viewModel, viewModel::onAllArticlesSearch)

}