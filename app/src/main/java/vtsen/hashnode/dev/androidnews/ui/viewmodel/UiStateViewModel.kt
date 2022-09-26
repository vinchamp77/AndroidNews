package vtsen.hashnode.dev.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiState

open class UiStateViewModel(protected val repository: ArticlesRepository) : ViewModel() {

    private val uiFlow = repository.statusFlow.map { repositoryStatus ->
        transformRepositoryStatus(repositoryStatus)
    }
    val uiStateFlow: StateFlow<UiState> = uiFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading
    )

    private fun transformRepositoryStatus(repositoryStatus: ArticlesRepositoryStatus): UiState {

        return when(repositoryStatus) {
            is ArticlesRepositoryStatus.Invalid,
            is ArticlesRepositoryStatus.IsLoading -> UiState.Loading
            is ArticlesRepositoryStatus.Success -> UiState.Success
            is ArticlesRepositoryStatus.Fail -> UiState.Error(R.string.no_internet)
        }
    }
}