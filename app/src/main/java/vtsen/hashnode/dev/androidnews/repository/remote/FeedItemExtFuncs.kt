package vtsen.hashnode.dev.androidnews.repository.remote

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity

fun List<FeedItem>.asArticleEntities() : List<ArticleEntity> {
    return map { item ->
        item.asArticleEntity()
    }
}

fun FeedItem.asArticleEntity() : ArticleEntity {
    return ArticleEntity(
        id = 0,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
    )
}
