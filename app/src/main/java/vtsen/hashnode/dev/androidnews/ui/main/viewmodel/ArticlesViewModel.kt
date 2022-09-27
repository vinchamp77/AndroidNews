package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiStateViewModel

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

    fun getArticle(articleId: Int) = repository.getArticle(articleId)
}