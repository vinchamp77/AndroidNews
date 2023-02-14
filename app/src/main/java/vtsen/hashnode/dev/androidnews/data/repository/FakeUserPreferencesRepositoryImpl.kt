package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.flow.Flow

class FakeUserPreferencesRepositoryImpl : UserPreferencesRepository {

    override fun getBookmarkArticles(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addBookmarkArticle(articleId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeBookmarkArticle(articleId: String) {
        TODO("Not yet implemented")
    }

    override fun getReadArticles(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addReadArticle(articleId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeReadArticle(articleId: String) {
        TODO("Not yet implemented")
    }
}