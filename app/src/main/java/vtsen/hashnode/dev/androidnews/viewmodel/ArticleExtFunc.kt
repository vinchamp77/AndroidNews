package vtsen.hashnode.dev.androidnews.viewmodel

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity

fun Article.asArticleEntity() : ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked,
    )
}

fun Article.asArticleEntity(bookmarked: Boolean) : ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked,
    )
}