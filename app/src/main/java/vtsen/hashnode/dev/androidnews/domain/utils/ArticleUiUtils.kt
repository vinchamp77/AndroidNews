/*
 * Copyright 2025 Vincent Tsen
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
package vtsen.hashnode.dev.androidnews.domain.utils

import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import java.util.Date

object ArticleUiUtils {
    fun makeFakeArticles(): List<ArticleUi> {
        val articles: MutableList<ArticleUi> = mutableListOf()
        repeat(10) {
            articles.add(createArticle())
        }
        return articles
    }

    fun createArticle(
        bookmarked: Boolean = false,
        read: Boolean = false,
    ): ArticleUi =
        ArticleUi(
            id = "how-to-implement-hilt-in-android-app",
            title = "How to Implement Hilt in Android App?",
            link = "https://vtsen.hashnode.dev/how-to-implement-hilt-in-android-app",
            pubDate = Date().time,
            image = "https://cdn.hashnode.com/res/hashnode/image/upload/v1643788167289/tf0hGfYSO.jpeg",
            feedTitle = "Android Kotlin Weekly",
            author = "Vincent Tsen",
            bookmarked = bookmarked,
            read = read,
        )
}
