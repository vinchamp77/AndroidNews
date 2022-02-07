package vtsen.hashnode.dev.androidnews.data

import vtsen.hashnode.dev.androidnews.utils.FeedItem

data class Article(
    val title: String?,
    val description: String?,
    val link: String?,
    val pubDate: String?,
    val image: String?,
)

fun List<FeedItem>.asArticles() : List<Article> {
    return map { item ->
        item.asArticle()
    }
}

fun FeedItem.asArticle() : Article {
    return Article(
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
    )
}
