package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository

class GetUnreadArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository
) {
    operator fun invoke(title: String? = null): Flow<List<Article>> {

        val combineFlow = articlesRepository.getAllArticles().combine(
            userPrefsRepository.getReadArticles()) { allArticles, readArticleIds ->

            var unreadArticles = mutableListOf<Article>()

            for (readArticleId in readArticleIds) {

                val unreadArticle = allArticles.find { article ->
                    article.id != readArticleId
                }

                unreadArticle?.run {
                    unreadArticles.add(unreadArticle)
                }
            }

            if(!title.isNullOrEmpty()) {
                val filteredUnreadArticles = unreadArticles.filter {article ->
                    article.title.contains(title)
                }

                unreadArticles = filteredUnreadArticles.toMutableList()
            }

            unreadArticles.toList()
        }

        return combineFlow
    }
}