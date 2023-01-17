package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

class UpdateArticleUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke(article: Article) = repository.updateArticle(article)
}