package vtsen.hashnode.dev.androidnews.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MainRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}