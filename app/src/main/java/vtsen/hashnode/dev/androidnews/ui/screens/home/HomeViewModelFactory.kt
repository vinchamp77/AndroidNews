package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: MainRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}