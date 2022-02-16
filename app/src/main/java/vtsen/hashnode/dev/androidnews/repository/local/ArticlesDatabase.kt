package vtsen.hashnode.dev.androidnews.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ArticleEntity::class, ArticleSettingsEntity::class],
    exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {

    protected abstract val dao: ArticlesDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: ArticlesDatabase

        fun getInstance(context: Context): ArticlesDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ArticlesDatabase::class.java,
                        "articles.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return INSTANCE
            }
        }
    }

    suspend fun getAll() = dao.getAll()

    suspend fun insertAll(articles: List<ArticleEntity>) = dao.insertAll(articles)

    suspend fun clear() {
        dao.clear()
        // reset auto increment of the primary key
        runSqlQuery("DELETE FROM sqlite_sequence WHERE name='${DatabaseConstants.ARTICLE_TABLE_NAME}'")
    }

    private fun runSqlQuery(value: String) {
        INSTANCE.openHelper.writableDatabase.execSQL(value)
    }
}