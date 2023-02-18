package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.domain.model.Article

interface ArticlesRepository {
    fun getStatus(): Flow<ArticlesRepoStatus>
    fun getAllArticles(): Flow<List<Article>>
    fun selectArticleById(id: String) : Flow<Article?>
    suspend fun refresh(): ArticlesRepoStatus
    fun clearStatus()
    suspend fun updateArticle(article: Article)
    fun getAllArticlesByTitle(title: String): Flow<List<Article>>
}