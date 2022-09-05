package vtsen.hashnode.dev.androidnews.domain.repository

sealed class ArticlesRepositoryStatus {
    object Success: ArticlesRepositoryStatus()
    object Fail: ArticlesRepositoryStatus()
}