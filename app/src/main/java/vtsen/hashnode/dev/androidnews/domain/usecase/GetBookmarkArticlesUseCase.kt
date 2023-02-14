package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository

class GetBookmarkArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository
) {
    operator fun invoke(title: String? = null): Flow<List<Article>> {

        val combineFlow = articlesRepository.allArticles.combine(
            userPrefsRepository.getBookmarkArticles()) { allArticles, bookmarkArticleIds ->

            var bookmarkedArticles = mutableListOf<Article>()

            for (bookmarkArticleId in bookmarkArticleIds) {

                val bookmarkedArticle = allArticles.find { article ->
                    article.id == bookmarkArticleId
                }

                bookmarkedArticle?.run {
                    bookmarkedArticles.add(bookmarkedArticle)
                }
            }

            if(!title.isNullOrEmpty()) {
                val filteredBookmarkArticles = bookmarkedArticles.filter {article ->
                    article.title.contains(title)
                }

                bookmarkedArticles = filteredBookmarkArticles.toMutableList()
            }

            bookmarkedArticles.toList()
        }

        return combineFlow
    }
}