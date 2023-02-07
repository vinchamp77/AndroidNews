package vtsen.hashnode.dev.androidnews.domain.repository

interface UserPreferencesRepository {
    suspend fun addBookmarkArticle(articleId: String)

    suspend fun removeBookmarkArticle(articleId: String)

    suspend fun addReadArticle(articleId: String)

    suspend fun removeReadArticle(articleId: String)
}
