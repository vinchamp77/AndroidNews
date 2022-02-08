package vtsen.hashnode.dev.androidnews.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<ArticleEntity>>
}