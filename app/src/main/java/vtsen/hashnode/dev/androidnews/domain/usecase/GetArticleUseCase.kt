package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

class GetArticleUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(id: String) = repository.selectArticleById(id)
}