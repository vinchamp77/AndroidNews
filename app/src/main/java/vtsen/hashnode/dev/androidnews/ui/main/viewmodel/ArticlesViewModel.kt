package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticlesUiState
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

open class ArticlesViewModel(
    protected val getArticleStatusUseCase: GetArticleStatusUseCase,
    protected val refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    protected val clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    protected val addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    protected val removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    protected val addReadArticlesUseCase: AddReadArticlesUseCase,
    protected val removeReadArticlesUseCase: RemoveReadArticlesUseCase,
    protected val getOneArticleUseCase: GetOneArticleUseCase,
) : ViewModel() {

    val uiState = getArticleStatusUseCase().map { repositoryStatus ->
        repositoryStatus.toArticlesUiState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArticlesUiState.Success
    )

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

    fun getArticle(articleId: String) = getOneArticleUseCase(articleId)
}

