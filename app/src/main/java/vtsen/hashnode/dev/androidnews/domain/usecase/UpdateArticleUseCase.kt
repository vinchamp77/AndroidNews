package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository

class UpdateArticleUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke(articleUi: ArticleUi) {
        //repository.updateArticle(articleUi)
    }
}