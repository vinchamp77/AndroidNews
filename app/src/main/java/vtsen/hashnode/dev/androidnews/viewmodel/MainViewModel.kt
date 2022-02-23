package vtsen.hashnode.dev.androidnews.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.repository.MainRepository
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.local.asArticles
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.utils.Utils

class MainViewModel(context: Context, preview: Boolean = false) : ViewModel() {

    private val repository = MainRepository(
        ArticlesDatabase.getInstance(context),
        WebService(),
    )
    // All articles
    private val articlesFlow = repository.articlesFlow.map { articleEntity ->
        articleEntity.asArticles()
    }
    var allArticles: List<Article>? by mutableStateOf(null)
        private set
    // Bookmarked articles
    private val bookmarkedArticlesFlow = repository.bookmarkedArticlesFlow.map { articleEntity ->
        articleEntity.asArticles()
    }
    var bookmarkedArticles: List<Article>? by mutableStateOf(null)
        private set
    // Unread articles
    private val unreadArticlesFlow = repository.unreadArticlesFlow.map { articleEntity ->
        articleEntity.asArticles()
    }
    var unreadArticles: List<Article>? by mutableStateOf(null)
        private set

    var showSnackBarStringId: Int? by mutableStateOf(null)
        private set

    init {
        if(preview) {
            mockPreviewArticles()
        } else {
            refresh()
            collectFlows()
        }
    }

    fun clearShowSnackBarStringId() {
        showSnackBarStringId = null
    }

    fun getArticle(id: Int): Article {
        val article = allArticles!!.find { article ->
            article.id == id
        }

        return article!!
    }

    fun onReadClick(id: Int) = viewModelScope.launch {
        val article = getArticle(id)
        repository.updateArticle(article.asArticleEntity(read = !article.read))
    }

    fun onBookmarkClick(id: Int) = viewModelScope.launch {
        val article = getArticle(id)
        repository.updateArticle(article.asArticleEntity(bookmarked = !article.bookmarked))
    }

    private fun mockPreviewArticles() {
        var articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle())
        }
        allArticles = articles

        articles = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle(bookmarked = true, read = true))
        }
        bookmarkedArticles = articles

        unreadArticles = articles
    }

    private fun refresh() {
        viewModelScope.launch {
            val status = repository.refresh()
            if (status == MainRepository.Status.FAIL) {
                showSnackBarStringId = R.string.no_internet
            }
        }
    }

    private fun collectFlows() {
        viewModelScope.launch {

            launch {
                articlesFlow.collect { articles ->
                    allArticles = articles
                }
            }

            launch {
                bookmarkedArticlesFlow.collect { articles ->
                    bookmarkedArticles = articles
                }
            }

            launch {
                unreadArticlesFlow.collect { articles ->
                    unreadArticles = articles
                }
            }
        }
    }
}