package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetBookmarkArticlesUseCase(
    private val getAllArticlesUseCase: GetAllArticlesUseCase
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {

        val allArticlesFlow = getAllArticlesUseCase()

        val bookmarkArticlesFlow = allArticlesFlow.map { articleUiList ->
            articleUiList.filter { articleUi ->
                if(title.isNullOrEmpty()) {
                    articleUi.bookmarked
                } else {
                    articleUi.bookmarked && articleUi.title.contains(title)
                 }
            }
        }

        return bookmarkArticlesFlow
    }
}