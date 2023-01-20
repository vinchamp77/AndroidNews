package vtsen.hashnode.dev.androidnews.data.mapper

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.domain.model.Article

fun List<ArticleEntity>.toArticles() : List<Article> {
    return map { articleEntity ->
        articleEntity.toArticle()
    }
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        id = link.toUrlPath(),
        title = title,
        link = link,
        author = author,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked,
        read = read,

        feedTitle = feedTitle,
    )
}
