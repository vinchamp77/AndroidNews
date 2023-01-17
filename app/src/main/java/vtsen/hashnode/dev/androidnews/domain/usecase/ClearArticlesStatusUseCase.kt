package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

class ClearArticlesStatusUseCase(private val repository: ArticlesRepository) {
    operator fun invoke() = repository.clearStatus()
}