package vtsen.hashnode.dev.androidnews.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.local.asArticleEntity
import vtsen.hashnode.dev.androidnews.repository.remote.ArticleFeed
import vtsen.hashnode.dev.androidnews.repository.remote.FeedParser
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.repository.remote.asArticleEntities

private const val TAG = "MainRepository"
private const val URL = "https://vtsen.hashnode.dev/rss.xml"

class MainRepository(
    private val database: ArticlesDatabase,
    private val webService: WebService,
) {
    enum class Status {
        SUCCESS,
        FAIL,
    }

    val articlesFlow = database.selectAllArticles()
    val unreadArticlesFlow = database.selectUnreadArticles()
    val bookmarkedArticlesFlow = database.selectBookmarkedArticles()

    suspend fun refresh(): Status = withContext(Dispatchers.IO) {

        var status = Status.SUCCESS

        try {
            val articlesFeed = fetchArticlesFeed()
            updateDatabase(articlesFeed.asArticleEntities())

        } catch(e: Exception) {
            e.printStackTrace()
            status = Status.FAIL
        }

        status
    }

    suspend fun updateArticle(articleEntity: ArticleEntity) = withContext(Dispatchers.IO) {
        database.updateArticle(articleEntity)
    }

    suspend fun getArticle(id: Int) = withContext(Dispatchers.IO) {
        database.selectArticleById(id)
    }

    private suspend fun fetchArticlesFeed() : List<ArticleFeed> {
        val xmlString = webService.getXMlString(URL)
        return FeedParser().parse(xmlString)
    }

    private suspend fun updateDatabase(articleEntities: List<ArticleEntity>) = coroutineScope  {
        for(articleEntity in articleEntities) {
            launch{
                val articleFound = database.selectArticleByLink(articleEntity.link)
                if(articleFound == null) {
                    database.insertArticle(articleEntity)
                } else {
                    //Important Note: articleEntity.id is different than the one in articleFound.id (database)
                    val data = articleEntity.asArticleEntity(articleFound.id)
                    database.updateArticle(data)
                }
            }
        }
    }
}