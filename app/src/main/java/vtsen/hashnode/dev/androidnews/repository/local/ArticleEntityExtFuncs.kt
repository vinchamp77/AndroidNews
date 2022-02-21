package vtsen.hashnode.dev.androidnews.repository.local

import vtsen.hashnode.dev.androidnews.viewmodel.Article

fun List<ArticleEntity>.asArticles() : List<Article> {
    return map { item ->
        item.asArticle()
    }
}

fun ArticleEntity.asArticle(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
        bookmarked = bookmarked,
    )
}
