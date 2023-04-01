package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

@Composable
fun SearchResultsScreen(
    viewModel: SearchArticlesViewModel,
    navigateToArticle: (ArticleUi) -> Unit,
) {
    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    if(articles != null) {

        val uiState: ArticlesUiState by viewModel.uiState.collectAsStateWithLifecycle()

        ArticlesScreen(
            articleUis = articles!!,
            noArticlesDescStrResId = R.string.no_search_articles_desc,
            isRefreshing = (uiState is ArticlesUiState.Loading),
            isSearching = isSearching,
            navigateToArticle = navigateToArticle,
            onRefresh = viewModel::refresh,
            onBookmarkClick = viewModel::onBookmarkClick,
            onReadClick = viewModel::onReadClick,
        )
    }
}