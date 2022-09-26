package vtsen.hashnode.dev.androidnews.ui.viewmodel

sealed interface UiState {
    object Loading : UiState
    data class Error(val msgResId: Int) : UiState
    object Success : UiState
}