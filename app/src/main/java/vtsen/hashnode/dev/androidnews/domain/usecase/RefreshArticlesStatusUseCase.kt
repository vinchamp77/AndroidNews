package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository

class RefreshArticlesStatusUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke() = repository.refresh()
}