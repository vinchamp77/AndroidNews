package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel

class ArticleViewModel(repository: ArticlesRepository, articleId: Int) : ArticlesViewModel(repository) {

    val articleStateFlow: StateFlow<List<Article>?> = repository.articlesFlow
        .map { articles ->
            articles.filter { article ->
                article.id == articleId
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )

}