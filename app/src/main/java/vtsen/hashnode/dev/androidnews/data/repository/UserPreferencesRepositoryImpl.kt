package vtsen.hashnode.dev.androidnews.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import vtsen.hashnode.dev.androidnews.UserPreferences
import vtsen.hashnode.dev.androidnews.data.local.UserPreferencesSerializer
import vtsen.hashnode.dev.androidnews.domain.repository.UserPreferencesRepository
import java.io.IOException

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer
)

class UserPreferencesRepositoryImpl private constructor(
    private val dataStore: DataStore<UserPreferences>
) : UserPreferencesRepository {

    private val tag: String = "UserPrefsRepo"

    companion object {
        @Volatile
        private lateinit var instance: UserPreferencesRepository

        fun getInstance(context: Context): UserPreferencesRepository {
            synchronized(this) {
                if(!::instance.isInitialized) {
                    instance = UserPreferencesRepositoryImpl(
                        context.userPreferencesStore
                    )
                }
                return instance
            }
        }
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(tag, "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun addBookmarkArticle(articleId: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().putBookmarkedArticleIds(articleId, true).build()
        }
    }

    override suspend fun removeBookmarkArticle(articleId: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().removeBookmarkedArticleIds(articleId).build()
        }
    }

    override suspend fun addReadArticle(articleId: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().putReadArticlesIds(articleId, true).build()
        }
    }

    override suspend fun removeReadArticle(articleId: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().removeReadArticlesIds(articleId).build()
        }
    }
}