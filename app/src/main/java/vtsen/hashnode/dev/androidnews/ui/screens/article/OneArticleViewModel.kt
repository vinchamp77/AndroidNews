package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class OneArticleViewModel(
    getArticleStatusUseCase: GetArticleStatusUseCase,
    refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    addReadArticlesUseCase: AddReadArticlesUseCase,
    removeReadArticlesUseCase: RemoveReadArticlesUseCase,
    getArticleUseCase: GetArticleUseCase,
    articleId: String,
) : ArticlesViewModel(
        getArticleStatusUseCase,
        refreshArticlesStatusUseCase,
        clearArticlesStatusUseCase,
        addBookmarkArticlesUseCase,
        removeBookmarkArticlesUseCase,
        addReadArticlesUseCase,
        removeReadArticlesUseCase,
        getArticleUseCase,
) {

    val article = getArticle(articleId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )
}