package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetOneArticleUseCase(
    private val getAllArticlesUseCase: GetAllArticlesUseCase
) {
    operator fun invoke(id: String) : Flow<ArticleUi> {
        val allArticlesFlow = getAllArticlesUseCase()

        val oneArticleFlow = allArticlesFlow
            .map { articleUiList ->
                articleUiList.filter { articleUi ->
                    articleUi.id == id
                 }
            }
            .map {
                it.first()
            }

        return oneArticleFlow
    }
}