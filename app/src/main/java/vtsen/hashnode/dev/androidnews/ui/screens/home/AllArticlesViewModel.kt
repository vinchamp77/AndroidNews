package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class AllArticlesViewModel(
    getAllArticlesUseCase: GetAllArticlesUseCase,
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

    val articles = getAllArticlesUseCase()
        .combine(searchQuery) { articles, searchQuery ->
            if(searchQuery.isBlank()) {
                articles
            } else {
                articles.filter { articleUi ->  
                    articleUi.title.contains(searchQuery)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null)
}

