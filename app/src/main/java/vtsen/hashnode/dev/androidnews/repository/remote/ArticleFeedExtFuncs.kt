package vtsen.hashnode.dev.androidnews.repository.remote

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.utils.Utils

fun List<ArticleFeed>.asArticleEntities() : List<ArticleEntity> {
    return map { feedItem ->
        feedItem.asArticleEntity()
    }
}

fun ArticleFeed.asArticleEntity() : ArticleEntity {
    return ArticleEntity(
        id = 0,
        title = feedItem.title,
        link = feedItem.link,
        author = feedItem.author,
        pubDate = Utils.parsePubDateStringToLong(feedItem.pubDate),
        image = feedItem.image,
        bookmarked = false,
        read = false,

        feedTitle = feedTitle,
    )
}
