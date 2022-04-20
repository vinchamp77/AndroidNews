package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.SqlArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: ArticlesRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}