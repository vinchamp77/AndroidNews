package vtsen.hashnode.dev.androidnews.data.mapper

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.data.remote.ArticleFeed
import vtsen.hashnode.dev.androidnews.utils.Utils

fun List<ArticleFeed>.toArticleEntities() : List<ArticleEntity> {
    return map { feedItem ->
        feedItem.toArticleEntity()
    }
}

fun ArticleFeed.toArticleEntity() : ArticleEntity {
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
