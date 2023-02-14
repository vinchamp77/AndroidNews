package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.utils.Utils

class FakeArticlesRepositoryImpl : ArticlesRepository {

    private var _status: ArticlesRepoStatus = ArticlesRepoStatus.Invalid
    override val status: Flow<ArticlesRepoStatus> = flow {
        while(true) {
            delay(1000)
            emit(_status)
        }
    }

    private var _allArticles: MutableList<Article> = mutableListOf()
    override val allArticles = flow {
        while(true){
            emit(_allArticles)
            delay(5000)
        }
    }

    private var _unreadArticles: MutableList<Article> = mutableListOf()
    override val unreadArticles = flow {
        while(true){
            emit(_unreadArticles)
            delay(5000)
        }
    }

    private var bookmarkedArticles: MutableList<Article> = mutableListOf()
    override val bookmarkArticles = flow {
        while(true){
            emit(bookmarkedArticles)
            delay(5000)
        }
    }

    init {
        makeFakeArticles()
    }

    override suspend fun refresh() : ArticlesRepoStatus {
        return ArticlesRepoStatus.Success(true)
    }

    override fun clearStatus() {
        _status = ArticlesRepoStatus.Invalid
    }

    override suspend fun updateArticle(article: Article) {}

    override fun selectArticleById(id: String): Flow<Article?> {
        val article = _allArticles.find { article ->
            article.id == id
        }

        val flow = flow {
            emit(article)
        }

        return flow
    }

    override  fun getAllArticlesByTitle(title: String): Flow<List<Article>>  {
        val articles = _allArticles.filter{ article ->
            article.title.contains(title)
        }

        return flow {
            emit(articles)
        }
    }

    override fun getUnreadArticlesByTitle(title: String): Flow<List<Article>> {
        val articles = _unreadArticles.filter{ article ->
            article.title.contains(title)
        }
        return flow {
            emit(articles)
        }
    }

    override fun getBookmarkedArticlesByTitle(title: String): Flow<List<Article>>  {
        val articles = bookmarkedArticles.filter{ article ->
            article.title.contains(title)
        }
        return flow {
            emit(articles)
        }
    }

    private fun makeFakeArticles() {
        var articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle())
        }
        _allArticles = articles

        articles = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle(bookmarked = true, read = true))
        }
        bookmarkedArticles = articles

        _unreadArticles = articles
    }
}