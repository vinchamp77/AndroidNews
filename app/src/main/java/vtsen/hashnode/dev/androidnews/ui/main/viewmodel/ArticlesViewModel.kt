package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus

open class ArticlesViewModel(protected val repository: ArticlesRepository) : ViewModel() {

    val uiState = repository.status.map { repositoryStatus ->
        transformRepositoryStatus(repositoryStatus)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArticlesUiState.Success
    )

    fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    fun clearStatus() {
        repository.clearStatus()
    }

    fun onReadClick(article: Article) = viewModelScope.launch {
        repository.updateArticle(article.copy(read = !article.read))
    }

    fun onBookmarkClick(article: Article) = viewModelScope.launch {
        repository.updateArticle(article.copy(bookmarked = !article.bookmarked))
    }

    fun getArticle(articleId: Int) = repository.getArticle(articleId)

    private fun transformRepositoryStatus(
        repositoryStatus: ArticlesRepositoryStatus
    ): ArticlesUiState {

        return when(repositoryStatus) {
            is ArticlesRepositoryStatus.Invalid -> ArticlesUiState.Invalid
            is ArticlesRepositoryStatus.IsLoading -> ArticlesUiState.Loading
            is ArticlesRepositoryStatus.Success -> ArticlesUiState.Success
            is ArticlesRepositoryStatus.Fail -> ArticlesUiState.Error(R.string.no_internet)
        }
    }
}