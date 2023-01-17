package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.usecase.*

@Suppress("UNCHECKED_CAST")
class OneArticleViewModelFactory(
    private val repository: ArticlesRepository,
    private val articleId: Int,
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OneArticleViewModel::class.java)) {
            return OneArticleViewModel(
                GetArticleStatusUseCase(repository),
                RefreshArticlesStatusUseCase(repository),
                ClearArticlesStatusUseCase(repository),
                UpdateArticleUseCase(repository),
                GetArticleUseCase(repository),
                articleId,
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}