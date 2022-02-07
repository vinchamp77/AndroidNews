package vtsen.hashnode.dev.androidnews.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository()
    val articles = repository.articles

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