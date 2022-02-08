package vtsen.hashnode.dev.androidnews.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity (
    @PrimaryKey val id: Int,

)