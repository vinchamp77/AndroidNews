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
package vtsen.hashnode.dev.androidnews.data.mapper

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.data.remote.ArticleFeed
import vtsen.hashnode.dev.androidnews.utils.Utils

fun List<ArticleFeed>.toArticleEntities(): List<ArticleEntity> {
    return map { feedItem ->
        feedItem.toArticleEntity()
    }
}

fun ArticleFeed.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = feedItem.link.toUrlPath(),
        title = feedItem.title,
        link = feedItem.link,
        author = feedItem.author,
        pubDate = Utils.parsePubDateStringToLong(feedItem.pubDate),
        image = feedItem.image,

        feedTitle = feedTitle,
    )
}
