package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getStatus(): Flow<ArticlesRepoStatus>
    fun getAllArticles(): Flow<List<ArticleRepo>>
    fun selectArticleById(id: String) : Flow<ArticleRepo?>
    suspend fun refresh(): ArticlesRepoStatus
    fun clearStatus()
    suspend fun updateArticle(article: ArticleRepo)
    fun getAllArticlesByTitle(title: String): Flow<List<ArticleRepo>>
}