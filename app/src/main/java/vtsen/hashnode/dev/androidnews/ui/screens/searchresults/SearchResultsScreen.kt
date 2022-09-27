package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchResultsScreen(
    viewModel: SearchArticlesViewModel,
    navigateToArticle: (Article) -> Unit,
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    if(articles != null) {

        val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

        ArticlesScreen(
            articles = articles!!,
            noArticlesDescStrResId = R.string.no_search_articles_desc,
            isRefreshing = (uiState is UiState.Loading),
            navigateToArticle = navigateToArticle,
            onRefresh = viewModel::refresh,
            onBookmarkClick = viewModel::onBookmarkClick,
            onReadClick = viewModel::onReadClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = SearchArticlesViewModel(repository, R.string.all_articles, "View Model")

    SearchResultsScreen(
        viewModel,
        navigateToArticle = {})
}
