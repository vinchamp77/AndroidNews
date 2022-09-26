package vtsen.hashnode.dev.androidnews.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus
import vtsen.hashnode.dev.androidnews.ui.screens.main.navigation.NavRoute.Article.id
import vtsen.hashnode.dev.androidnews.utils.Utils

open class ArticlesViewModel(repository: ArticlesRepository) : UiStateViewModel(repository) {

    fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    fun onReadClick(article: Article) = viewModelScope.launch {
        repository.updateArticle(article.copy(read = !article.read))
    }

    fun onBookmarkClick(article: Article) = viewModelScope.launch {
        repository.updateArticle(article.copy(bookmarked = !article.bookmarked))
    }
}