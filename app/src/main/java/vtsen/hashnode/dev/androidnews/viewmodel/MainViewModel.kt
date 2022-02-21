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

    private val articlesFlow = repository.articlesFlow.map { articleEntity ->
        articleEntity.asArticles()
    }
    var articles: List<Article>? by mutableStateOf(null)
        private set

    var showSnackBarStringId: Int? by mutableStateOf(null)
        private set

    init {
        if(preview) {
            mockPreviewArticles()
        } else {
            refresh()
        }
    }

    fun clearShowSnackBarStringId() {
        showSnackBarStringId = null
    }

    fun getArticle(id: Int): Article {
        val article = articles!!.find { article ->
            article.id == id
        }

        return article!!
    }

    fun onBookmarkClick(id: Int) = viewModelScope.launch {
        val article = getArticle(id)
        repository.updateArticle(article.asArticleEntity(!article.bookmarked))
    }

    private fun mockPreviewArticles() {
        val articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle())
        }
        this.articles = articles
    }

    private fun refresh() {
        viewModelScope.launch {
            val status = repository.refresh()
            if (status == MainRepository.Status.FAIL) {
                showSnackBarStringId = R.string.no_internet
            }

            articlesFlow.collect {
                //articles = null
                articles = it
            }
        }
    }
}