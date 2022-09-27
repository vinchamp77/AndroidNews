package vtsen.hashnode.dev.androidnews.ui.screens.home

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
fun HomeScreen(
    viewModel: AllArticlesViewModel,
    navigateToArticle: (Article) -> Unit,
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    if(articles != null) {

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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