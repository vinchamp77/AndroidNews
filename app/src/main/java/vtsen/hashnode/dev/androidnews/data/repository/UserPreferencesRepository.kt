package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getBookmarkArticles(): Flow<List<String>>
    suspend fun addBookmarkArticle(articleId: String)
    suspend fun removeBookmarkArticle(articleId: String)

    fun getReadArticles(): Flow<List<String>>
    suspend fun addReadArticle(articleId: String)
    suspend fun removeReadArticle(articleId: String)
}
