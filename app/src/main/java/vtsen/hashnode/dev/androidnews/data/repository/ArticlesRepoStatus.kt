package vtsen.hashnode.dev.androidnews.data.repository

sealed interface ArticlesRepoStatus {
    object Invalid: ArticlesRepoStatus
    object IsLoading: ArticlesRepoStatus
    data class Success(val withNewArticles: Boolean): ArticlesRepoStatus
    object Fail: ArticlesRepoStatus
}