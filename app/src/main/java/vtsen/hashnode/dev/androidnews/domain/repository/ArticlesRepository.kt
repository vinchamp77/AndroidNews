package vtsen.hashnode.dev.androidnews.domain.repository

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article

interface ArticlesRepository {

    val status: Flow<ArticlesRepositoryStatus>

    val allArticles: Flow<List<Article>>

    val unreadArticles: Flow<List<Article>>

    val bookmarkArticles: Flow<List<Article>>

    fun getArticle(id: Int) : Flow<Article>

    suspend fun refresh(): ArticlesRepositoryStatus

    suspend fun updateArticle(article: Article)

    fun getAllArticlesByTitle(title: String): Flow<List<Article>>

    fun getUnreadArticlesByTitle(title: String): Flow<List<Article>>

    fun getBookmarkedArticlesByTitle(title: String): Flow<List<Article>>
}