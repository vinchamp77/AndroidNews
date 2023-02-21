package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUiList

class GetBookmarkArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {

        val flow =  articlesRepository.getAllArticles()
        val allArticlesFlow = flow.map { articleRepoList ->
            articleRepoList.toArticleUiList()
        }

        val combineFlow = allArticlesFlow.combine(
            userPrefsRepository.getBookmarkArticles()) { allArticles, bookmarkArticleIds ->

            var bookmarkedArticleUis = mutableListOf<ArticleUi>()

            for (bookmarkArticleId in bookmarkArticleIds) {

                val bookmarkedArticle = allArticles.find { article ->
                    article.id == bookmarkArticleId
                }

                bookmarkedArticle?.run {
                    bookmarkedArticleUis.add(bookmarkedArticle)
                }
            }

            if(!title.isNullOrEmpty()) {
                val filteredBookmarkArticles = bookmarkedArticleUis.filter { article ->
                    article.title.contains(title)
                }

                bookmarkedArticleUis = filteredBookmarkArticles.toMutableList()
            }

            bookmarkedArticleUis.toList()
        }

        return combineFlow
    }
}