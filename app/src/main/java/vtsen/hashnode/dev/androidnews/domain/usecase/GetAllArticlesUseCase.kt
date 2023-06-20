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
package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticleUi
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetAllArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {
        val allArticlesFlow =
            if (title.isNullOrEmpty()) {
                articlesRepository.getAllArticles()
            } else {
                articlesRepository.getAllArticlesByTitle(title)
            }

        val readArticlesFlow = userPreferencesRepository.getReadArticles()
        val bookmarkArticlesFlow = userPreferencesRepository.getBookmarkArticles()

        val combineFlow = combine(
            allArticlesFlow,
            readArticlesFlow,
            bookmarkArticlesFlow,
        ) { allArticles, readArticleIds, bookmarkArticleIds ->

            val allArticleUis = mutableListOf<ArticleUi>()

            for (article in allArticles) {
                val isArticleRead = readArticleIds.contains(article.id)
                val isArticleBookmarked = bookmarkArticleIds.contains(article.id)
                val articleUi = article.toArticleUi(isArticleBookmarked, isArticleRead)
                allArticleUis.add(articleUi)
            }

            allArticleUis.toList()
        }

        return combineFlow
    }
}
