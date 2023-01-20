package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.mapper.toArticlesUiState

open class ArticlesViewModel(
    protected val getArticleStatusUseCase: GetArticleStatusUseCase,
    protected val refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    protected val clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    protected val updateArticleUseCase: UpdateArticleUseCase,
    protected val getArticleUseCase: GetArticleUseCase,
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

    fun onReadClick(article: Article) = viewModelScope.launch {
        updateArticleUseCase(article.copy(read = !article.read))
    }

    fun onBookmarkClick(article: Article) = viewModelScope.launch {
        updateArticleUseCase(article.copy(bookmarked = !article.bookmarked))
    }

    fun getArticle(articleId: String) = getArticleUseCase(articleId)
}

