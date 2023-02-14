package vtsen.hashnode.dev.androidnews.domain.model

sealed interface ArticlesUiState {
    object Invalid: ArticlesUiState
    object Loading: ArticlesUiState
    data class Error(val msgResId: Int): ArticlesUiState
    object Success: ArticlesUiState
}