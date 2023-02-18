package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.utils.Utils

class FakeArticlesRepositoryImpl : ArticlesRepository {

    private var _status: ArticlesRepoStatus = ArticlesRepoStatus.Invalid
    override fun getStatus(): Flow<ArticlesRepoStatus> {
        return flow {
            while (true) {
                delay(500)
                emit(_status)
            }
        }
    }


    private var _allArticles: MutableList<Article> = mutableListOf()
    override fun getAllArticles(): Flow<List<Article>> {
        return flow {
            while(true){
                emit(_allArticles)
                delay(5000)
            }
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
    }
}