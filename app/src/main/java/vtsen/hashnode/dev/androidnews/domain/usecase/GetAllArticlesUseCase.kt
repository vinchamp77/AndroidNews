package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUiList
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetAllArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {

        val flow =
            if (title.isNullOrEmpty()) {
                articlesRepository.getAllArticles()
            }
            else {
                articlesRepository.getAllArticlesByTitle(title)
            }

        val allArticlesFlow = flow.map { articleRepoList ->
            articleRepoList.toArticleUiList()
        }
        val readArticlesFlow = userPreferencesRepository.getReadArticles()
        val bookmarkArticlesFlow = userPreferencesRepository.getBookmarkArticles()

        val combineFlow = combine(
            allArticlesFlow,
            readArticlesFlow,
            bookmarkArticlesFlow) { allArticles, readArticleIds, bookmarkArticleIds ->

            val allArticleUis = mutableListOf<ArticleUi>()

            for (article in allArticles) {

                val isArticleRead = readArticleIds.contains(article.id)
                val isArticleBookmarked = bookmarkArticleIds.contains(article.id)
                allArticleUis.add(article.copy(read = isArticleRead, bookmarked = isArticleBookmarked))
            }

            allArticleUis.toList()
        }

        return combineFlow
    }
}