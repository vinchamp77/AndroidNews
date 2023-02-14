package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepoStatus

class GetArticleStatusUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(): Flow<ArticlesRepoStatus> = repository.getStatus()
}