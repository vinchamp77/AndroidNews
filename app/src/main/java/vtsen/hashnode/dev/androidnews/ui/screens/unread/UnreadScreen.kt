package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UnreadScreen(
    viewModel: UnreadArticlesViewModel,
    navigateToArticle: (Article) -> Unit,
) {
    val articles by viewModel.articlesStateFlow.collectAsStateWithLifecycle()

    if(articles != null) {

        val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

        ArticlesScreen(
            articles = articles!!,
            noArticlesDescStrResId = R.string.no_articles_desc,
            isRefreshing = (uiState is UiState.Loading),
            navigateToArticle = navigateToArticle,
            onRefresh = viewModel::refresh,
            onBookmarkClick = viewModel::onBookmarkClick,
            onReadClick = viewModel::onReadClick,
        )
    }
}