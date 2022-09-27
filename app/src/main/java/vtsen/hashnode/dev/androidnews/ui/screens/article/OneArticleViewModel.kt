package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.viewmodel.ArticlesViewModel

class OneArticleViewModel(
    repository: ArticlesRepository,
    articleId: Int) : ArticlesViewModel(repository) {

    val article = getArticle(articleId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
         )
}