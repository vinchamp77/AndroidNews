package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun SearchResultsScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Article) -> Unit,
) {
    if(viewModel.searchedArticles == null) return

    ArticlesScreen(
        articles = viewModel.searchedArticles!! ,
        noArticlesDescStrResId = R.string.no_search_articles_desc,
        isRefreshing = viewModel.isRefreshing,
        navigateToArticle = navigateToArticle,
        onRefresh = viewModel::refresh,
        onBookmarkClick = viewModel::onBookmarkClick,
        onReadClick = viewModel::onReadClick,
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = MainViewModel(repository, useFakeData = true)

    SearchResultsScreen(
        viewModel,
        navigateToArticle = {})
}
