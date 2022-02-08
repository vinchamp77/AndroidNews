package vtsen.hashnode.dev.androidnews.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ArticleEntity::class],
    exportSchema = true)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract val dao: ArticlesDao

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
}