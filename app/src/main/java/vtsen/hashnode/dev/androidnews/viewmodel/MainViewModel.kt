package vtsen.hashnode.dev.androidnews.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.repository.MainRepository
import vtsen.hashnode.dev.androidnews.repository.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.repository.remote.WebService

class MainViewModel(context: Context) : ViewModel() {

    var snackBarStringId: Int? by mutableStateOf(null)
    private set

    private val repository = MainRepository(
        ArticlesDatabase.getInstance(context),
        WebService(),
    )
    val articles = repository.articles

    init {
        refresh()
    }

    fun mockData() {
        repository.mockData()
    }

    fun clearSnackBar() {
        snackBarStringId = null
    }

    fun getArticle(id: String): Article {
        val article = articles.value.find { article ->
            article.id == id
        }
        return article!!
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