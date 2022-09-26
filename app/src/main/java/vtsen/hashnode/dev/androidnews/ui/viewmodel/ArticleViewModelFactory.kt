package vtsen.hashnode.dev.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel

@Suppress("UNCHECKED_CAST")
class ArticleViewModelFactory(private val repository: ArticlesRepository, private val articleId: Int)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(repository, articleId) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}