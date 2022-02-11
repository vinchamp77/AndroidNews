package vtsen.hashnode.dev.androidnews.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

private const val TABLE_NAME = "article"

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM article")
    suspend fun getAll(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()
}

