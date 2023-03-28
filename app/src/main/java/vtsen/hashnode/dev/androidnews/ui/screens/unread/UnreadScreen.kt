package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

@Composable
fun UnreadScreen(
    viewModel: UnreadArticlesViewModel,
    navigateToArticle: (ArticleUi) -> Unit,
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    if(articles != null) {

        val uiState: ArticlesUiState by viewModel.uiState.collectAsStateWithLifecycle()

        ArticlesScreen(
            articleUis = articles!!,
            noArticlesDescStrResId = R.string.no_unread_articles_desc,
            isRefreshing = (uiState is ArticlesUiState.Loading),
            navigateToArticle = navigateToArticle,
            onRefresh = viewModel::refresh,
            onBookmarkClick = viewModel::onBookmarkClick,
            onReadClick = viewModel::onReadClick,
        )
    }
}