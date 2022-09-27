package vtsen.hashnode.dev.androidnews.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.screens.article.OneArticleViewModel

@Suppress("UNCHECKED_CAST")
class OneArticleViewModelFactory(
    private val repository: ArticlesRepository,
    private val articleId: Int,
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OneArticleViewModel::class.java)) {
            return OneArticleViewModel(repository, articleId) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}