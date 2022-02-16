package vtsen.hashnode.dev.androidnews.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_settings")
data class ArticleSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val link: String,
    val bookmarked: Boolean,
)