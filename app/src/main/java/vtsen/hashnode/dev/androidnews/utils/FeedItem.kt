package vtsen.hashnode.dev.androidnews.utils

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.viewmodel.Article

data class FeedItem(
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val image: String)


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

fun List<FeedItem>.asArticleEntities() : List<ArticleEntity> {
    var id = 0
    return map { item ->
        ++id
        item.asArticleEntity(id.toString())
    }
}

fun FeedItem.asArticleEntity(id: String) : ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
    )
}
