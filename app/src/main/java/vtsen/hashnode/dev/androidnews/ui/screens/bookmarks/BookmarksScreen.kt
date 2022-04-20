package vtsen.hashnode.dev.androidnews.ui.screens.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.SqlArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeViewModel

@Composable
fun BookmarksScreen(
    viewModel: HomeViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.bookmarkedArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.bookmarkedArticles!! ,
        navigateToArticle = navigateToArticle,
        R.string.no_bookmarked_articles_desc,
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = SqlArticlesRepository(
        ArticlesDatabase.getInstance(LocalContext.current),
        WebService(),
    )
    val viewModel = HomeViewModel(repository, useFakeData = true)

    BookmarksScreen(
        viewModel,
        navigateToArticle = {})
}
