package vtsen.hashnode.dev.androidnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ArticleEntity::class]
)
abstract class ArticlesDatabase : RoomDatabase() {

    protected abstract val dao: ArticlesDao

    fun selectAllArticles() = dao.selectAllArticles()
    fun selectAllArticlesByTitle(title: String) = dao.selectAllArticlesByTitle("%$title%")

    fun selectBookmarkedArticles() = dao.selectBookmarkedArticles()
    fun selectBookmarkedArticlesByTitle(title: String) = dao.selectBookmarkedArticlesByTitle("%$title%")

    fun selectUnreadArticles() = dao.selectUnreadArticles()
    fun selectUnreadArticlesByTitle(title: String) = dao.selectUnreadArticlesByTitle("%$title%")

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
        openHelper.writableDatabase.execSQL(value)
    }
}