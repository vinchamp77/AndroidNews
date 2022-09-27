package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository

@Suppress("UNCHECKED_CAST")
class SearchArticlesViewModelFactory(
    private val repository: ArticlesRepository,
    private val titleResId: Int,
    private val query: String,
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SearchArticlesViewModel::class.java)) {
            return SearchArticlesViewModel(repository, titleResId, query) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}