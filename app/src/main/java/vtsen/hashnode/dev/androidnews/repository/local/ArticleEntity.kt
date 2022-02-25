package vtsen.hashnode.dev.androidnews.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DatabaseConstants.ARTICLE_TABLE_NAME)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val link: String,
    val pubDate: Long,
    val image: String,
    val bookmarked: Boolean,
    val read: Boolean,

    val feedTitle: String,
    val author: String,
)