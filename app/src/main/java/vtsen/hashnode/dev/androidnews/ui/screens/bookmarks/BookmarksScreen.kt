package vtsen.hashnode.dev.androidnews.ui.screens.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.ui.screens.home.ArticlesScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun BookmarksScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.bookmarkedArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.bookmarkedArticles!! ,
        navigateToArticle = navigateToArticle)
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, preview = true)

    BookmarksScreen(
        viewModel,
        navigateToArticle = {})
}
