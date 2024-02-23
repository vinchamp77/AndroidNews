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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserPreferencesRepositoryImpl : UserPreferencesRepository {
    private val bookmarkArticleIds = mutableListOf<String>()
    private val readArticleIds = mutableListOf<String>()

    init {
    }

    override fun getBookmarkArticles(): Flow<List<String>> {
        return flow {
            emit(bookmarkArticleIds)
        }
    }

    override suspend fun addBookmarkArticle(articleId: String) {
        if (!bookmarkArticleIds.contains(articleId)) {
            bookmarkArticleIds.add(articleId)
        }
    }

    override suspend fun removeBookmarkArticle(articleId: String) {
        bookmarkArticleIds.remove(articleId)
    }

    override fun getReadArticles(): Flow<List<String>> {
        return flow {
            emit(readArticleIds)
        }
    }

    override suspend fun addReadArticle(articleId: String) {
        if (!readArticleIds.contains(articleId)) {
            readArticleIds.add(articleId)
        }
    }

    override suspend fun removeReadArticle(articleId: String) {
        readArticleIds.remove(articleId)
    }
}
