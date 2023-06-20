/*
 * Copyright 2023 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.UserPreferences
import vtsen.hashnode.dev.androidnews.data.local.UserPreferencesSerializer
import java.io.IOException

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer,
)

class UserPreferencesRepositoryImpl private constructor(
    private val dataStore: DataStore<UserPreferences>,
) : UserPreferencesRepository {

    private val tag: String = "UserPrefsRepo"

    companion object {
        @Volatile
        private lateinit var instance: UserPreferencesRepository

        fun getInstance(context: Context): UserPreferencesRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = UserPreferencesRepositoryImpl(
                        context.applicationContext.userPreferencesStore,
                    )
                }
                return instance
            }
        }
    }

    private val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(tag, "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override fun getBookmarkArticles(): Flow<List<String>> {
        return userPreferencesFlow.map { userPreferences ->
            userPreferences.bookmarkedArticleIdsMap.keys.toList()
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

    override fun getReadArticles(): Flow<List<String>> {
        return userPreferencesFlow.map { userPreferences ->
            userPreferences.readArticlesIdsMap.keys.toList()
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
