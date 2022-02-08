package vtsen.hashnode.dev.androidnews.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import vtsen.hashnode.dev.androidnews.main.Article

@Entity(tableName = "article")
data class ArticleEntity (
    @PrimaryKey val id: Int,

    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val image: String,
)

fun List<ArticleEntity>.asArticles() : List<Article> {
    return map { item ->
        item.asArticle()
    }
}

fun ArticleEntity.asArticle(): Article {
    return Article(
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
    )
}
