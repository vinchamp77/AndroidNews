package vtsen.hashnode.dev.androidnews.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.repository.MainRepository
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.local.asArticles
import vtsen.hashnode.dev.androidnews.repository.remote.WebService
import vtsen.hashnode.dev.androidnews.utils.Utils

class MainViewModel(context: Context, preview: Boolean = false) : ViewModel() {

    var snackBarStringId: Int? by mutableStateOf(null)
    private set

    private val repository = MainRepository(
        ArticlesDatabase.getInstance(context),
        WebService(),
    )

    private val _previewArticles = MutableStateFlow<List<Article>>(listOf())
    private val articlesFlow = repository.articles.map { articleEntity ->
        articleEntity.asArticles()
    }
    val articlesStateFlow = if (preview)
            _previewArticles
        else
            articlesFlow.stateIn(viewModelScope, WhileSubscribed(), null)

    init {
        if(preview) {
            mockPreviewData()
        } else {
            refresh()
        }
    }

    fun clearSnackBar() {
        snackBarStringId = null
    }

    fun getArticle(id: Int): Article {
        val article = articlesStateFlow.value!!.find { article ->
            article.id == id
        }
        return article!!
    }

    //TODO:
    fun onBookmarkClick(id: Int) {
        val article = getArticle(id)
        article.bookmarked = true

        viewModelScope.launch {
            repository.updateArticle(article)
        }

        var article1 = getArticle(id)
        article1 = article1
    }

    private fun mockPreviewData() {
        val articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle())
        }
        _previewArticles.value = articles
    }

    private fun refresh() {
        viewModelScope.launch {
            val status = repository.refresh()
            if (status == MainRepository.Status.FAIL) {
                snackBarStringId = R.string.no_internet
            }
        }
    }
}