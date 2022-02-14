package vtsen.hashnode.dev.androidnews.utils

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity

data class FeedItem(
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val image: String)

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
