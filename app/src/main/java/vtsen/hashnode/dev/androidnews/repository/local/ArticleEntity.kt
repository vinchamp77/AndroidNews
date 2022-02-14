package vtsen.hashnode.dev.androidnews.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import vtsen.hashnode.dev.androidnews.viewmodel.Article

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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
        id = id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate,
        image = image,
    )
}
