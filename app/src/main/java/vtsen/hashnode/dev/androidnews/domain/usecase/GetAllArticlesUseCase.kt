package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(title: String? = null): Flow<List<Article>> {

        if (title.isNullOrEmpty()) {
            return repository.getAllArticles()
        }

        return repository.getAllArticlesByTitle(title)
    }
}