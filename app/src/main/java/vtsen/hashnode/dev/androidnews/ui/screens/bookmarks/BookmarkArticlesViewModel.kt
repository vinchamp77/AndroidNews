package vtsen.hashnode.dev.androidnews.ui.screens.bookmarks

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel

class BookmarkArticlesViewModel(repository: ArticlesRepository) : ArticlesViewModel(repository) {

    val articles = repository.bookmarkArticles
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )

}