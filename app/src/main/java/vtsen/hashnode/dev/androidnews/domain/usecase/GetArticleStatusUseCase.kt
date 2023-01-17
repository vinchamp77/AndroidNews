package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus

class GetArticleStatusUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(): Flow<ArticlesRepositoryStatus> = repository.status
}