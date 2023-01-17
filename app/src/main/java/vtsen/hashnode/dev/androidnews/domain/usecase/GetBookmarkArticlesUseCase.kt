package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

class GetBookmarkArticlesUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(title: String? = null): Flow<List<Article>> {

        if (title.isNullOrEmpty()) {
            return repository.bookmarkArticles
        }

        return repository.getBookmarkedArticlesByTitle(title)
    }
}