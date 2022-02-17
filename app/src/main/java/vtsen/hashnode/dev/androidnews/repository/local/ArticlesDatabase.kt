package vtsen.hashnode.dev.androidnews.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ArticleEntity::class],
    exportSchema = false)
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
    fun selectArticleByLink(link: String) = dao.selectArticleByLink(link)
    fun selectArticleById(id: Int) = dao.selectArticleById(id)
    fun insertArticle(article: ArticleEntity) = dao.insertArticle(article)
    fun updateArticle(article: ArticleEntity) = dao.updateArticle(article)
    fun deleteAllArticles() {
        dao.deleteAllArticles()
        // reset auto increment of the primary key
        runSqlQuery("DELETE FROM sqlite_sequence WHERE name='${DatabaseConstants.ARTICLE_TABLE_NAME}'")
    }

    private fun runSqlQuery(value: String) {
        instance.openHelper.writableDatabase.execSQL(value)
    }
}