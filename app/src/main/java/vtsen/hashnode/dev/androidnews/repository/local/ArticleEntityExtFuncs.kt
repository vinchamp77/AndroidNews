package vtsen.hashnode.dev.androidnews.repository.local

import vtsen.hashnode.dev.androidnews.viewmodel.Article

fun List<ArticleEntity>.asArticles() : List<Article> {
    return map { articleEntity ->
        articleEntity.asArticle()
    }
}

fun ArticleEntity.asArticle(): Article {
    return Article(
        id = id,
        title = title,
        link = link,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked,
        read = read,

        feedTitle = feedTitle,
        author = author,
    )
}
