package vtsen.hashnode.dev.androidnews.domain.repository

sealed class ArticlesRepositoryStatus {
    object Invalid: ArticlesRepositoryStatus()
    object IsLoading: ArticlesRepositoryStatus()
    data class Success(val withNewArticles: Boolean): ArticlesRepositoryStatus()
    object Fail: ArticlesRepositoryStatus()
}