package vtsen.hashnode.dev.androidnews.viewmodel

import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity

fun Article.asArticleEntity(
    bookmarked: Boolean? = null,
    read: Boolean? = null,
) : ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked ?: this.bookmarked,
        read = read ?: this.read,
    )
}