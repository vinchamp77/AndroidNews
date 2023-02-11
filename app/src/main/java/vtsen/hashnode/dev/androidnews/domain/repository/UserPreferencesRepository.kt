package vtsen.hashnode.dev.androidnews.domain.repository

import kotlinx.coroutines.flow.Flow
import vtsen.hashnode.dev.androidnews.UserPreferences

interface UserPreferencesRepository {

    val userPreferencesFlow: Flow<UserPreferences>

    suspend fun addBookmarkArticle(articleId: String)

    suspend fun removeBookmarkArticle(articleId: String)

    suspend fun addReadArticle(articleId: String)

    suspend fun removeReadArticle(articleId: String)
}
