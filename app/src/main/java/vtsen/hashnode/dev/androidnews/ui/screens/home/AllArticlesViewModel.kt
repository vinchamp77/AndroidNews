package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiStateViewModel

class AllArticlesViewModel(repository: ArticlesRepository) : ArticlesViewModel(repository) {

    val articlesStateFlow: StateFlow<List<Article>?> = repository.articlesFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )
}