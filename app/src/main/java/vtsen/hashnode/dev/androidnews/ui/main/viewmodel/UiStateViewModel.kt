package vtsen.hashnode.dev.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus

open class UiStateViewModel(protected val repository: ArticlesRepository) : ViewModel() {

    val uiState = repository.status.map { repositoryStatus ->
        transformRepositoryStatus(repositoryStatus)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Success
    )

    private fun transformRepositoryStatus(
        repositoryStatus: ArticlesRepositoryStatus): UiState {

        return when(repositoryStatus) {
            is ArticlesRepositoryStatus.Invalid,
            is ArticlesRepositoryStatus.IsLoading -> UiState.Loading
            is ArticlesRepositoryStatus.Success -> UiState.Success
            is ArticlesRepositoryStatus.Fail -> UiState.Error(R.string.no_internet)
        }
    }
}

sealed interface UiState {
    object Loading : UiState
    data class Error(val msgResId: Int) : UiState
    object Success : UiState
}