package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel

@Suppress("UNCHECKED_CAST")
class ArticlesViewModelFactory(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository,
)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
            return ArticlesViewModel(
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetArticleUseCase(articlesRepository),
            ) as T
        }
        else if (modelClass.isAssignableFrom(AllArticlesViewModel::class.java)) {
            return AllArticlesViewModel(
                GetAllArticlesUseCase(articlesRepository, userPrefsRepository),
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetArticleUseCase(articlesRepository),
            ) as T
        }
        else if (modelClass.isAssignableFrom(UnreadArticlesViewModel::class.java)) {
            return UnreadArticlesViewModel(
                GetUnreadArticlesUseCase(articlesRepository, userPrefsRepository),
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetArticleUseCase(articlesRepository),
            ) as T
        }
        else if (modelClass.isAssignableFrom(BookmarkArticlesViewModel::class.java)) {
            return BookmarkArticlesViewModel(
                GetBookmarkArticlesUseCase(articlesRepository, userPrefsRepository),
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetArticleUseCase(articlesRepository),
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}