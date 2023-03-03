package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository

class ClearArticlesStatusUseCase(private val articlesRepository: ArticlesRepository) {
    operator fun invoke() = articlesRepository.clearStatus()
}