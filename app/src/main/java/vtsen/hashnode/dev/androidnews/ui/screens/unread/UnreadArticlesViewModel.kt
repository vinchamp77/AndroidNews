package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
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
    val articles = searchQuery
        //.debounce(1000) // required if it is network call
        .onEach {_isSearching.value = true}
        .combine(getUnreadArticlesUseCase()) { searchQuery, articles ->
            if(searchQuery.isBlank()) {
                articles
            } else {
                //delay(2000) // simulate network delay
                articles.filter { articleUi ->
                    articleUi.title.contains(searchQuery, ignoreCase = true)
                }
            }
        }
        .onEach { _isSearching.value = false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null)
}