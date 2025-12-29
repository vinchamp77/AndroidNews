/*
 * Copyright 2025 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 3,
    entities = [ArticleEntity::class],
)
abstract class ArticlesDatabase : RoomDatabase() {
    protected abstract val dao: ArticlesDao

    companion object {
        @Volatile
        private lateinit var instance: ArticlesDatabase

        fun getInstance(context: Context): ArticlesDatabase {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance =
                        Room
                            .databaseBuilder(
                                context.applicationContext,
                                ArticlesDatabase::class.java,
                                "articles.db",
                            ).fallbackToDestructiveMigration()
                            .build()
                }

                return instance
            }
        }
    }

    fun selectAllArticles() = dao.selectAllArticles()

    fun selectAllArticlesByTitle(title: String) = dao.selectAllArticlesByTitle("%$title%")

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
