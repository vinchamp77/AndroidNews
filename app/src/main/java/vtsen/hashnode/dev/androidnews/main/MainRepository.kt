package vtsen.hashnode.dev.androidnews.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vtsen.hashnode.dev.androidnews.remote.WebService
import vtsen.hashnode.dev.androidnews.utils.FeedParser
import vtsen.hashnode.dev.androidnews.utils.Utils

private const val TAG = "MainRepository"

class MainRepository {

    private val _articles = MutableStateFlow<List<Article>>(listOf())
    val articles: StateFlow<List<Article>> = _articles

    private val url = "https://vtsen.hashnode.dev/rss.xml"
    private val webService = WebService()

    suspend fun refresh() {
        val xmlString = webService.getXMlString(url)
        val feedItems = FeedParser().parse(xmlString)
        _articles.value = feedItems.asArticles()
    }

    fun mockData() {

        val articles: MutableList<Article> = mutableListOf()

        repeat(10) {
            articles.add(Utils.createArticle())
        }
        _articles.value = articles
    }
}