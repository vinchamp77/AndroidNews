package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class AllArticlesViewModel(repository: ArticlesRepository) : ArticlesViewModel(repository) {

    val articles = repository.allArticles.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )
}