package vtsen.hashnode.dev.androidnews.data.mapper

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.data.repository.ArticleRepo

fun List<ArticleEntity>.toArticleRepoList() : List<ArticleRepo> {
    return map { articleEntity ->
        articleEntity.toArticleRepo()
    }
}

fun ArticleEntity.toArticleRepo(): ArticleRepo {
    return ArticleRepo(
        id = link.toUrlPath(),
        title = title,
        link = link,
        author = author,
        pubDate = pubDate,
        image = image,
        feedTitle = feedTitle,
    )
}
