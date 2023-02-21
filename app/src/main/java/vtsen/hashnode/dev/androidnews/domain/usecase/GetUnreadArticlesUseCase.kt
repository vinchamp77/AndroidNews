package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUiList

class GetUnreadArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {

        val flow =  articlesRepository.getAllArticles()
        val allArticlesFlow = flow.map { articleRepoList ->
            articleRepoList.toArticleUiList()
        }

        val combineFlow = allArticlesFlow.combine(
            userPrefsRepository.getReadArticles()) { allArticles, readArticleIds ->

            var unreadArticleUis = mutableListOf<ArticleUi>()

            for (readArticleId in readArticleIds) {

                val unreadArticle = allArticles.find { article ->
                    article.id != readArticleId
                }

                unreadArticle?.run {
                    unreadArticleUis.add(unreadArticle)
                }
            }

            if(!title.isNullOrEmpty()) {
                val filteredUnreadArticles = unreadArticleUis.filter { article ->
                    article.title.contains(title)
                }

                unreadArticleUis = filteredUnreadArticles.toMutableList()
            }

            unreadArticleUis.toList()
        }

        return combineFlow
    }
}