package vtsen.hashnode.dev.androidnews.domain.utils

import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import java.util.*

object ArticleUiUtils {

    fun makeFakeArticles(): List<ArticleUi> {
        val articles: MutableList<ArticleUi> = mutableListOf()
        repeat(10) {
            articles.add(createArticle())
        }
        return articles
    }

    fun createArticle(bookmarked: Boolean = false, read: Boolean = false): ArticleUi {
        return ArticleUi(
            id = "how-to-implement-hilt-in-android-app",
            title = "How to Implement Hilt in Android App?",
            link = "https://vtsen.hashnode.dev/how-to-implement-hilt-in-android-app",
            pubDate = Date().time,
            image = "https://cdn.hashnode.com/res/hashnode/image/upload/v1643788167289/tf0hGfYSO.jpeg",
            feedTitle = "Android Kotlin Weekly",
            author = "Vincent Tsen",
            bookmarked = bookmarked,
            read = read,
        )
    }
}