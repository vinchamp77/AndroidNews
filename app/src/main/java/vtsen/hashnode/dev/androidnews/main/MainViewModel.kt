package vtsen.hashnode.dev.androidnews.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.local.ArticlesDatabase

class MainViewModel(app: Application?) : ViewModel() {

    private val repository = MainRepository()
    val articles = repository.articles

    val database = app?.run {
        ArticlesDatabase.getInstance(app)
    }

    init {
        refresh()
    }

    fun mockData() {
        repository.mockData()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }
}