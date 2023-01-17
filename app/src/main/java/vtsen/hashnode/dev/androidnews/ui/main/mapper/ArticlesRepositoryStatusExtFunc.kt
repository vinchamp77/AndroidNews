package vtsen.hashnode.dev.androidnews.ui.main.mapper

import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesUiState

fun ArticlesRepositoryStatus.toArticlesUiState(): ArticlesUiState {
    return when(this) {
        is ArticlesRepositoryStatus.Invalid -> ArticlesUiState.Invalid
        is ArticlesRepositoryStatus.IsLoading -> ArticlesUiState.Loading
        is ArticlesRepositoryStatus.Success -> ArticlesUiState.Success
        is ArticlesRepositoryStatus.Fail -> ArticlesUiState.Error(R.string.no_internet)
    }
}