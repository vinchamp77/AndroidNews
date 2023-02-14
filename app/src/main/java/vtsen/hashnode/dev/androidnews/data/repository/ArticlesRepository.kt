package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article

interface ArticlesRepository {

    val allArticles: Flow<List<Article>>

    val unreadArticles: Flow<List<Article>>

    val bookmarkArticles: Flow<List<Article>>
    fun getStatus(): Flow<ArticlesRepoStatus>

    fun selectArticleById(id: String) : Flow<Article?>

    suspend fun refresh(): ArticlesRepoStatus

    fun clearStatus()

    suspend fun updateArticle(article: Article)

    fun getAllArticlesByTitle(title: String): Flow<List<Article>>

    fun getUnreadArticlesByTitle(title: String): Flow<List<Article>>

    fun getBookmarkedArticlesByTitle(title: String): Flow<List<Article>>
}