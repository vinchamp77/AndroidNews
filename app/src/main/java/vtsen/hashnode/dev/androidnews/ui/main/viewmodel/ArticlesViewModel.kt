package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState
import vtsen.hashnode.dev.androidnews.domain.usecase.*

abstract class ArticlesViewModel(
    getArticleStatusUseCase: GetArticleStatusUseCase,
    protected val refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    protected val clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    protected val addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    protected val removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    protected val addReadArticlesUseCase: AddReadArticlesUseCase,
    protected val removeReadArticlesUseCase: RemoveReadArticlesUseCase,
) : ViewModel() {

    val uiState = getArticleStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ArticlesUiState.Success
    )

    protected val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    protected val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchQuery(query: String)
    {
        _searchQuery.value = query
    }

    fun refresh() = viewModelScope.launch {
        refreshArticlesStatusUseCase()
    }

    fun clearStatus() = clearArticlesStatusUseCase()

    fun onReadClick(articleUi: ArticleUi) = viewModelScope.launch {
        if(articleUi.read) {
            removeReadArticlesUseCase(articleUi.id)
        } else {
            addReadArticlesUseCase(articleUi.id)
        }
    }

    fun onBookmarkClick(articleUi: ArticleUi) = viewModelScope.launch {
        if(articleUi.bookmarked) {
            removeBookmarkArticlesUseCase(articleUi.id)
        } else {
            addBookmarkArticlesUseCase(articleUi.id)
        }
    }
}

