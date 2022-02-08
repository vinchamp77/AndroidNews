package vtsen.hashnode.dev.androidnews.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<ArticleEntity>
}