package vtsen.hashnode.dev.androidnews.domain.repository

sealed class ArticlesRepositoryStatus {
    object Invalid: ArticlesRepositoryStatus()
    object Success: ArticlesRepositoryStatus()
    object Fail: ArticlesRepositoryStatus()
}