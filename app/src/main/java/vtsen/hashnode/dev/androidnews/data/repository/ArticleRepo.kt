package vtsen.hashnode.dev.androidnews.data.repository

data class ArticleRepo(
    val id: String,
    val title: String,
    val link: String,
    val author: String,
    val pubDate: Long,
    val image: String,
    val feedTitle: String,
)