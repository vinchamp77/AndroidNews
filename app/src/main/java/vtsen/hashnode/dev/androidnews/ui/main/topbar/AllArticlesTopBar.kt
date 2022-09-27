package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.R

@Composable
fun AllArticlesTopBar(navHostController: NavHostController) {

    ArticlesTopBar(navHostController, R.string.all_articles)

}