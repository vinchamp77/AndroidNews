package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

sealed interface ArticlesUiState {
    object Invalid: ArticlesUiState
    object Loading: ArticlesUiState
    data class Error(val msgResId: Int): ArticlesUiState
    object Success: ArticlesUiState
}