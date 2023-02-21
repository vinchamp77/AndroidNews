package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.usecase.*

@Suppress("UNCHECKED_CAST")
class SearchArticlesViewModelFactory(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository,
    private val searchResultTitleResId: Int,
    private val query: String,
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SearchArticlesViewModel::class.java)) {
            return SearchArticlesViewModel(
                GetAllArticlesUseCase(articlesRepository, userPrefsRepository),
                GetBookmarkArticlesUseCase(articlesRepository, userPrefsRepository),
                GetUnreadArticlesUseCase(articlesRepository, userPrefsRepository),
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetArticleUseCase(articlesRepository),
                searchResultTitleResId,
                query) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}