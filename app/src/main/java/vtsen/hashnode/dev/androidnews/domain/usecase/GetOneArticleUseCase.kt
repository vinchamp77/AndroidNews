package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUi
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetOneArticleUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(id: String) : Flow<ArticleUi> {
        val articleFlow = articlesRepository.selectArticleById(id)
        val readArticlesFlow = userPreferencesRepository.getReadArticles()
        val bookmarkArticlesFlow = userPreferencesRepository.getBookmarkArticles()

        val combineFlow = combine(articleFlow, readArticlesFlow, bookmarkArticlesFlow) { article, readArticleIds, bookmarkArticlesId ->

            val read = readArticleIds.contains(article!!.id)
            val bookmark = bookmarkArticlesId.contains(article.id)
            article.toArticleUi(bookmark, read)
        }

        return combineFlow
    }
}