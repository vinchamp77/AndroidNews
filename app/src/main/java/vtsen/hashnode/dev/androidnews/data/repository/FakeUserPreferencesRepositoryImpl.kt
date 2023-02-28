package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserPreferencesRepositoryImpl : UserPreferencesRepository {

    private val bookmarkArticleIds = mutableListOf<String>()
    private val readArticleIds = mutableListOf<String>()

    init {

    }

    override fun getBookmarkArticles(): Flow<List<String>> {
        return flow {
            emit(bookmarkArticleIds)
        }
    }

    override suspend fun addBookmarkArticle(articleId: String) {
        if(!bookmarkArticleIds.contains(articleId))
            bookmarkArticleIds.add(articleId)
    }

    override suspend fun removeBookmarkArticle(articleId: String) {
        bookmarkArticleIds.remove(articleId)
    }

    override fun getReadArticles(): Flow<List<String>> {
        return flow {
            emit(readArticleIds)
        }
    }

    override suspend fun addReadArticle(articleId: String) {
        if(!readArticleIds.contains(articleId))
            readArticleIds.add(articleId)
    }

    override suspend fun removeReadArticle(articleId: String) {
        readArticleIds.remove(articleId)
    }
}