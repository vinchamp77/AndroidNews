package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel

class UnreadArticlesViewModel(repository: ArticlesRepository) : ArticlesViewModel(repository) {

    val articlesStateFlow: StateFlow<List<Article>?> = repository.articlesFlow
        .map { articles ->
            articles.filter { article ->
                !article.read
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )

}