package vtsen.hashnode.dev.androidnews.utils

import vtsen.hashnode.dev.androidnews.data.Article

object Utils {
    fun createArticle() : Article {
        return Article(
            title = "How to Implement Hilt in Android App?",
            description = "",
            link = "https://vtsen.hashnode.dev/how-to-implement-hilt-in-android-app",
            pubDate = "Sat, 05 Feb 2022 00:27:48 GMT",
            image = "https://cdn.hashnode.com/res/hashnode/image/upload/v1643788167289/tf0hGfYSO.jpeg",
        )
    }
}