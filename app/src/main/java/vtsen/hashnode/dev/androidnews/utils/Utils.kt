package vtsen.hashnode.dev.androidnews.utils

import vtsen.hashnode.dev.androidnews.domain.model.Article
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object Utils {
    fun makeFakeArticles(): List<Article> {
        val articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(createArticle())
        }
        return articles
    }

    fun createArticle(bookmarked: Boolean = false, read: Boolean = false) : Article {
        return Article(
            id = 0,
            title = "How to Implement Hilt in Android App?",
            link = "https://vtsen.hashnode.dev/how-to-implement-hilt-in-android-app",
            pubDate = Date().time,
            image = "https://cdn.hashnode.com/res/hashnode/image/upload/v1643788167289/tf0hGfYSO.jpeg",
            bookmarked = bookmarked,
            read = read,

            feedTitle = "Android Kotlin Weekly",
            author = "Vincent Tsen",
        )
    }

    private val pubDateFormats = listOf(
        "EEE, dd MMM yyyy HH:mm:ss zzz",
        "EEE, dd MMM yyyy HH:mm zzz",
        "EEE, dd MMM yyyy HH:mm zzz"
    )

    fun parsePubDateStringToLong(value: String) = parsePubDateStringToDate(value).time
    private fun parsePubDateStringToDate(value: String) : Date {
        for (dateFormat in pubDateFormats) {
            try {
                val articleDateFormat = SimpleDateFormat(dateFormat, Locale.US)
                return articleDateFormat.parse(value)
            } catch (e: ParseException) {
            }
        }

        return Date()
    }

    fun parseDateLongToElapsedTime(value: Long, shortOutput: Boolean = false) : String {
        val diff = Date().time - value
        val time: String
        when {
            // Minutes.
            diff < 1000L * 60L * 60L -> {
                val longEnding =
                    if ((diff / (1000L * 60L)) == 1L) " minute ago" else " minutes ago"
                time = "${diff / (1000L * 60L)}" + if (shortOutput) "m" else longEnding
            }
            // Hours.
            diff < 1000L * 60L * 60L * 24L -> {
                val longEnding =
                    if ((diff / (1000L * 60L * 60L)) == 1L) " hour ago" else " hours ago"
                time = "${diff / (1000L * 60L * 60L)}" + if (shortOutput) "h" else longEnding
            }
            // Days.
            diff < 1000L * 60L * 60L * 24L * 30L -> {
                val longEnding =
                    if ((diff / (1000L * 60L * 60L * 24L)) == 1L) " day ago" else " days ago"
                time = "${diff / (1000L * 60L * 60L * 24L)}" + if (shortOutput) "d" else longEnding
            }
            // Months.
            diff < 1000L * 60L * 60L * 24L * 30L * 12L -> {
                val longEnding =
                    if ((diff / (1000L * 60L * 60L * 24L * 30L)) == 1L) " month ago" else " months ago"
                time =
                    "${diff / (1000L * 60L * 60L * 24L * 30L)}" + if (shortOutput) "M" else longEnding
            }
            // Years.
            else -> {
                val longEnding =
                    if ((diff / (1000L * 60L * 60L * 24L * 30L * 12L)) == 1L) " year ago" else " years ago"
                time =
                    "${diff / (1000L * 60L * 60L * 24L * 30L * 12L)}" + if (shortOutput) "Y" else longEnding
            }
        }

        return time
    }
}