package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class SearchArticlesViewModel(
    getAllArticlesUseCase: GetAllArticlesUseCase,
    getBookmarkArticlesUseCase: GetBookmarkArticlesUseCase,
    getUnreadArticlesUseCase: GetUnreadArticlesUseCase,
    getArticleStatusUseCase: GetArticleStatusUseCase,
    refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    updateArticleUseCase: UpdateArticleUseCase,
    getArticleUseCase: GetArticleUseCase,
    searchResultTitleResId: Int,
    query: String,
) : ArticlesViewModel(
        getArticleStatusUseCase,
        refreshArticlesStatusUseCase,
        clearArticlesStatusUseCase,
        updateArticleUseCase,
        getArticleUseCase,
) {

    val articles = when (searchResultTitleResId) {

        R.string.all_articles -> {
            getAllArticlesUseCase(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        R.string.unread_articles -> {
            getUnreadArticlesUseCase(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        R.string.bookmarked_articles -> {
            getBookmarkArticlesUseCase(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        else -> {
            throw Exception("Unexpected titleResId: $searchResultTitleResId")
        }
    }
}