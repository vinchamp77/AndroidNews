package vtsen.hashnode.dev.androidnews.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.remote.FeedItem
import vtsen.hashnode.dev.androidnews.repository.remote.FeedParser
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.repository.remote.asArticleEntities
import vtsen.hashnode.dev.androidnews.viewmodel.Article
import vtsen.hashnode.dev.androidnews.viewmodel.asArticleEntity

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

    val articles = database.selectAllArticles()

    suspend fun refresh(): Status = withContext(Dispatchers.IO) {

        var status = Status.SUCCESS

        try {
            val feedItems = fetchFeedItems()
            updateDatabase(feedItems.asArticleEntities())

        } catch(e: Exception) {
            e.printStackTrace()
            status = Status.FAIL
        }

        status
    }

    suspend fun updateArticle(article: Article) = withContext(Dispatchers.IO) {
        database.updateArticle(article.asArticleEntity())
    }

    private suspend fun fetchFeedItems() : List<FeedItem> {
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
                    database.updateArticle(articleEntity)
                }
            }
        }
    }
}