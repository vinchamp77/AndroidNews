package vtsen.hashnode.dev.androidnews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 2,
    entities = [ArticleEntity::class]
)
abstract class ArticlesDatabase : RoomDatabase() {

    protected abstract val dao: ArticlesDao

    companion object {
        @Volatile
        private lateinit var instance: ArticlesDatabase

        fun getInstance(context: Context): ArticlesDatabase {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticlesDatabase::class.java,
                        "articles.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }

    fun selectAllArticles() = dao.selectAllArticles()
    fun selectAllArticlesByTitle(title: String) = dao.selectAllArticlesByTitle("%$title%")

    fun selectBookmarkedArticles() = dao.selectBookmarkedArticles()
    fun selectBookmarkedArticlesByTitle(title: String) = dao.selectBookmarkedArticlesByTitle("%$title%")

    fun selectUnreadArticles() = dao.selectUnreadArticles()
    fun selectUnreadArticlesByTitle(title: String) = dao.selectUnreadArticlesByTitle("%$title%")

    fun getArticleById(link: String) = dao.getArticleById(link)
    fun selectArticleById(id: String) = dao.selectArticleById(id)

    fun insertArticle(article: ArticleEntity) = dao.insertArticle(article)
    fun updateArticle(article: ArticleEntity) = dao.updateArticle(article)
    fun deleteAllArticles() {
        dao.deleteAllArticles()
        // reset auto increment of the primary key
        runSqlQuery("DELETE FROM sqlite_sequence WHERE name='${DatabaseConstants.ARTICLE_TABLE_NAME}'")
    }

    private fun runSqlQuery(value: String) {
        openHelper.writableDatabase.execSQL(value)
    }
}