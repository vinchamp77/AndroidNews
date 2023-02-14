package vtsen.hashnode.dev.androidnews.domain.mapper

import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepoStatus
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

fun ArticlesRepoStatus.toArticlesUiState(): ArticlesUiState {
    return when(this) {
        is ArticlesRepoStatus.Invalid -> ArticlesUiState.Invalid
        is ArticlesRepoStatus.IsLoading -> ArticlesUiState.Loading
        is ArticlesRepoStatus.Success -> ArticlesUiState.Success
        is ArticlesRepoStatus.Fail -> ArticlesUiState.Error(R.string.no_internet)
    }
}