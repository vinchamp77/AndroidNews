package vtsen.hashnode.dev.androidnews.repository.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM ${DatabaseConstants.ARTICLE_TABLE_NAME} ORDER by pubDate DESC")
    fun selectAllArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ${DatabaseConstants.ARTICLE_TABLE_NAME} WHERE read = 0 ORDER by pubDate DESC")
    fun selectUnreadArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ${DatabaseConstants.ARTICLE_TABLE_NAME} WHERE bookmarked = 1 ORDER by pubDate DESC")
    fun selectBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ${DatabaseConstants.ARTICLE_TABLE_NAME} WHERE id = :id")
    fun selectArticleById(id: Int): ArticleEntity

    @Query("SELECT * FROM ${DatabaseConstants.ARTICLE_TABLE_NAME} WHERE link= :link")
    fun selectArticleByLink(link: String): ArticleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: ArticleEntity)

    @Update
    fun updateArticle(article: ArticleEntity)

    @Query("DELETE FROM ${DatabaseConstants.ARTICLE_TABLE_NAME}")
    fun deleteAllArticles()
}

