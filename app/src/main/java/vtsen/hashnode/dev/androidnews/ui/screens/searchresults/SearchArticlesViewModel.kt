package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel

class SearchArticlesViewModel(
    repository: ArticlesRepository,
    titleResId: Int,
    query: String,
) : ArticlesViewModel(repository) {

    val articles = when (titleResId) {

        R.string.all_articles -> {
            repository.getAllArticlesByTitle(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        R.string.unread_articles -> {
            repository.getUnreadArticlesByTitle(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        R.string.bookmarked_articles -> {
            repository.getBookmarkedArticlesByTitle(query)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                )
        }

        else -> {
            throw Exception("Unexpected titleResId: $titleResId")
        }
    }
}