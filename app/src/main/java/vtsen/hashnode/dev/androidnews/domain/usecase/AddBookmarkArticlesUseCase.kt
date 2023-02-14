package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository

class AddBookmarkArticlesUseCase(
    private val userPrefsRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(articleId: String) {
        userPrefsRepository.addBookmarkArticle(articleId)
    }
}