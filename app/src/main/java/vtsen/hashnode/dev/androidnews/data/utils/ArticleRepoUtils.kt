package vtsen.hashnode.dev.androidnews.data.utils

import vtsen.hashnode.dev.androidnews.data.repository.ArticleRepo
import java.util.*

object ArticleRepoUtils {
    fun makeFakeArticles(): List<ArticleRepo> {
        val articles: MutableList<ArticleRepo> = mutableListOf()
        repeat(10) {
            articles.add(createArticle())
        }
        return articles
    }

    fun createArticle() : ArticleRepo {
        return ArticleRepo(
            id = "how-to-implement-hilt-in-android-app",
            title = "How to Implement Hilt in Android App?",
            link = "https://vtsen.hashnode.dev/how-to-implement-hilt-in-android-app",
            pubDate = Date().time,
            image = "https://cdn.hashnode.com/res/hashnode/image/upload/v1643788167289/tf0hGfYSO.jpeg",
            feedTitle = "Android Kotlin Weekly",
            author = "Vincent Tsen",
        )
    }
}