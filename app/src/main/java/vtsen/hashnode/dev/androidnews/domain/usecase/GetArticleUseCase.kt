package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUi

class GetArticleUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(id: String) = repository.selectArticleById(id).map { articleRepo ->
        articleRepo?.toArticleUi()
    }
}