package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class UnreadArticlesViewModel(
    getUnreadArticlesUseCase: GetUnreadArticlesUseCase,
    getArticleStatusUseCase: GetArticleStatusUseCase,
    refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    addReadArticlesUseCase: AddReadArticlesUseCase,
    removeReadArticlesUseCase: RemoveReadArticlesUseCase,
) : ArticlesViewModel(
        getArticleStatusUseCase,
        refreshArticlesStatusUseCase,
        clearArticlesStatusUseCase,
        addBookmarkArticlesUseCase,
        removeBookmarkArticlesUseCase,
        addReadArticlesUseCase,
        removeReadArticlesUseCase,
) {

    val articles = getUnreadArticlesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}