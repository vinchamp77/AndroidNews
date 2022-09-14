package vtsen.hashnode.dev.androidnews.data.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus
import vtsen.hashnode.dev.androidnews.utils.Utils

class FakeArticlesRepositoryImpl() : ArticlesRepository {

    private var allArticles: MutableList<Article> = mutableListOf()
    private var bookmarkedArticles: MutableList<Article> = mutableListOf()
    private var unreadArticles: MutableList<Article> = mutableListOf()

    override val articlesFlow = flow {
        while(true){
            emit(allArticles)
            delay(5000)
        }
    }

    override val unreadArticlesFlow = flow {
        while(true){
            emit(unreadArticles)
            delay(5000)
        }
    }

    override val bookmarkedArticlesFlow = flow {
        while(true){
            emit(bookmarkedArticles)
            delay(5000)
        }
    }

    init {
        makeFakeArticles()
    }

    override suspend fun refresh() : ArticlesRepositoryStatus {
        return ArticlesRepositoryStatus.Success(true)
    }

    override suspend fun updateArticle(article: Article) {}

    override suspend fun getArticle(id: Int): Article {
        val article = allArticles.find { article ->
            article.id == id
        }

        return article!!
    }

    override suspend fun getAllArticlesByTitle(title: String): List<Article>  {
        val articles = allArticles.filter{ article ->
            article.title.contains(title)
        }
        return articles
    }

    override suspend fun getUnreadArticlesByTitle(title: String): List<Article>  {
        val articles = unreadArticles.filter{ article ->
            article.title.contains(title)
        }
        return articles
    }

    override suspend fun getBookmarkedArticlesByTitle(title: String): List<Article>  {
        val articles = bookmarkedArticles.filter{ article ->
            article.title.contains(title)
        }
        return articles
    }

    private fun makeFakeArticles() {
        var articles: MutableList<Article> = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle())
        }
        allArticles = articles

        articles = mutableListOf()
        repeat(10) {
            articles.add(Utils.createArticle(bookmarked = true, read = true))
        }
        bookmarkedArticles = articles

        unreadArticles = articles
    }
}