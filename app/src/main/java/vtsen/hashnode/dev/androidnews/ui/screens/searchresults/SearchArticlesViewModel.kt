package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class SearchArticlesViewModel(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
    private val getBookmarkArticlesUseCase: GetBookmarkArticlesUseCase,
    private val getUnreadArticlesUseCase: GetUnreadArticlesUseCase,
    getArticleStatusUseCase: GetArticleStatusUseCase,
    refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    addReadArticlesUseCase: AddReadArticlesUseCase,
    removeReadArticlesUseCase: RemoveReadArticlesUseCase,
    private val searchResultTitleResId: Int,
    private val query: String,
) : ArticlesViewModel(
        getArticleStatusUseCase,
        refreshArticlesStatusUseCase,
        clearArticlesStatusUseCase,
        addBookmarkArticlesUseCase,
        removeBookmarkArticlesUseCase,
        addReadArticlesUseCase,
        removeReadArticlesUseCase,
) {

    val articles = getArticlesFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )
    private fun getArticlesFlow() = when (searchResultTitleResId) {

        R.string.all_articles -> {
            getAllArticlesUseCase(query)
        }

        R.string.unread_articles -> {
            getUnreadArticlesUseCase(query)
        }

        R.string.bookmarked_articles -> {
            getBookmarkArticlesUseCase(query)
        }

        else -> {
            throw Exception("Unexpected titleResId: $searchResultTitleResId")
        }
    }
}