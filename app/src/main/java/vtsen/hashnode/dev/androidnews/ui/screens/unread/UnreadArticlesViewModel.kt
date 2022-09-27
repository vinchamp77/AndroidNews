package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class UnreadArticlesViewModel(repository: ArticlesRepository) : ArticlesViewModel(repository) {

    val articlesStateFlow = repository.unreadArticles
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )
}