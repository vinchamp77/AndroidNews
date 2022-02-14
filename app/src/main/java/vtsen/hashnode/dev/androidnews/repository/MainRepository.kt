package vtsen.hashnode.dev.androidnews.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.local.asArticles
import vtsen.hashnode.dev.androidnews.repository.remote.FeedParser
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.repository.remote.asArticleEntities
import vtsen.hashnode.dev.androidnews.utils.Utils
import vtsen.hashnode.dev.androidnews.viewmodel.Article

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

    private val _articles = MutableStateFlow<List<Article>>(listOf())
    val articles: StateFlow<List<Article>> = _articles

    suspend fun refresh(): Status {

        var status = Status.SUCCESS

        withContext(Dispatchers.IO) {

            try {
                loadFeedItems()
            } catch(e: Exception) {
                status = Status.FAIL
            }

            loadDatabase()
        }

        return status
    }

    fun mockData() {

        val articles: MutableList<Article> = mutableListOf()

        repeat(10) {
            articles.add(Utils.createArticle())
        }
        _articles.value = articles
    }

    private suspend fun loadFeedItems() {
        val xmlString = webService.getXMlString(URL)
        val feedItems =  FeedParser().parse(xmlString)
        val articleEntities = feedItems.asArticleEntities()
        database.dao.clear()
        database.dao.insertAll(articleEntities)
    }

    private suspend fun loadDatabase() {
        _articles.value = database.dao.getAll().asArticles()
    }
}