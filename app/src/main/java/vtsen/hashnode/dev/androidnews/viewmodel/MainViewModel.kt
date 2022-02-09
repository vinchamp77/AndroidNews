package vtsen.hashnode.dev.androidnews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.remote.WebService
import vtsen.hashnode.dev.androidnews.repository.MainRepository

class MainViewModel(context: Context) : ViewModel() {

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

    private fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }
}