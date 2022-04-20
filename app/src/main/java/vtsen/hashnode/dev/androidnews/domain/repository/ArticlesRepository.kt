package vtsen.hashnode.dev.androidnews.domain.repository

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article

interface ArticlesRepository {

    enum class Status {
        SUCCESS,
        FAIL,
    }

    val articlesFlow: Flow<List<Article>>

    val unreadArticlesFlow: Flow<List<Article>>

    val bookmarkedArticlesFlow: Flow<List<Article>>

    suspend fun refresh(): Status

    suspend fun updateArticle(article: Article)

    suspend fun getArticle(id: Int) : Article

    suspend fun getAllArticlesByTitle(title: String): List<Article>

    suspend fun getUnreadArticlesByTitle(title: String): List<Article>

    suspend fun getBookmarkedArticlesByTitle(title: String): List<Article>
}