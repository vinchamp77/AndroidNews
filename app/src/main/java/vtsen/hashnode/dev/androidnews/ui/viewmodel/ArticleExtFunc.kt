package vtsen.hashnode.dev.androidnews.ui.viewmodel

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity

fun Article.asArticleEntity(
    bookmarked: Boolean? = null,
    read: Boolean? = null,
) : ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        link = link,
        author = author,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked ?: this.bookmarked,
        read = read ?: this.read,

        feedTitle = feedTitle,
    )
}