package vtsen.hashnode.dev.androidnews.repository

import kotlinx.coroutines.*
import vtsen.hashnode.dev.androidnews.repository.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.local.asArticleEntity
import vtsen.hashnode.dev.androidnews.repository.remote.ArticleFeed
import vtsen.hashnode.dev.androidnews.repository.remote.FeedParser
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.repository.remote.asArticleEntities


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

    private val urls = listOf(
        "https://vtsen.hashnode.dev/rss.xml",
    )

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

    suspend fun getAllArticlesByTitle(title: String): List<ArticleEntity> = withContext(Dispatchers.IO) {
        return@withContext database.selectAllArticlesByTitle(title)
    }

    suspend fun getUnreadArticlesByTitle(title: String): List<ArticleEntity> = withContext(Dispatchers.IO) {
        return@withContext database.selectUnreadArticlesByTitle(title)
    }

    suspend fun getBookmarkedArticlesByTitle(title: String): List<ArticleEntity> = withContext(Dispatchers.IO) {
        return@withContext database.selectBookmarkedArticlesByTitle(title)
    }

    private suspend fun fetchArticlesFeed() : List<ArticleFeed> = coroutineScope {
        val results = mutableListOf<ArticleFeed>()
        val jobs = mutableListOf<Job>()

        for(url in urls) {
            val job = launch {
                val xmlString = webService.getXMlString(url)
                val articleFeeds = FeedParser().parse(xmlString)
                results.addAll(articleFeeds)
            }

            jobs.add(job)
        }

        jobs.joinAll()

        return@coroutineScope results
    }

    private suspend fun updateDatabase(articleEntities: List<ArticleEntity>) = coroutineScope  {
        for(articleEntity in articleEntities) {
            launch{
                val articleFound = database.selectArticleByLink(articleEntity.link)
                if(articleFound == null) {
                    database.insertArticle(articleEntity)
                } else {
                    //Important Note:
                    // (1) articleEntity.id is different than the one in articleFound.id (database)
                    // (2) We want to keep the saved bookmarked and read articles, do not want to overwrites it
                    val data = articleEntity.asArticleEntity(
                        articleFound.id,
                        articleFound.bookmarked,
                        articleFound.read,
                    )
                    database.updateArticle(data)
                }
            }
        }
    }
}