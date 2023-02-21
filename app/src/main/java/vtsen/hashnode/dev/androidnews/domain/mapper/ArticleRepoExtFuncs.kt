package vtsen.hashnode.dev.androidnews.domain.mapper

import vtsen.hashnode.dev.androidnews.data.mapper.toUrlPath
import vtsen.hashnode.dev.androidnews.data.repository.ArticleRepo
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

fun List<ArticleRepo>.toArticleUiList(): List<ArticleUi>{
    return map { articleRepo ->
        articleRepo.toArticleUi()
    }
}

fun ArticleRepo.toArticleUi(
    bookmarked: Boolean = false,
    read: Boolean = false,
) : ArticleUi {

    return ArticleUi(
        id = link.toUrlPath(),
        title = title,
        link = link,
        author = author,
        pubDate = pubDate,
        image = image,
        feedTitle = feedTitle,
        bookmarked = bookmarked,
        read = read,
    )
}