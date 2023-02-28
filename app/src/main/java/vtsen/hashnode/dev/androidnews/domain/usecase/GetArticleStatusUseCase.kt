package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticlesUiState
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

class GetArticleStatusUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(): Flow<ArticlesUiState> {
        return repository.getStatus().map { articlesRepoStatus ->
            articlesRepoStatus.toArticlesUiState()
        }
    }
}